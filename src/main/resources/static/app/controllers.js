var GEOSERVER_URL = 'http://34.226.38.202:8080/geoserver/wms';
var CURRENT_DISTRICTS_LAYER_NAME = 'opengeo:mapview';
var AT_AWARD_DISTRICTS_LAYER_NAME = 'opengeo:mapview_at_award';
var ALL_VALUE = 'All';
var DEFAULT_FISCAL_YEAR = [ 2017 ];

(function(angular) {
  var AppController = function($rootScope, $scope, $http, $httpParamSerializer,
      Agency, Bureau, Subcommittee, State, Congress, Year, Program) {
    $scope.data = {
      isMemberAtAward: false
    };

    $scope.wmsSource = getDistrictGrantsSource();

    $scope.map = getMap($scope.wmsSource);

    initializeRightPanel();
    initializeBottomPanel();

    $scope.buildParam = function(){
      var viewparams = _.chain([
          { filter: 'fiscal_year' },
          { filter: 'subcommittee' },
          { filter: 'agency' },
          { filter: 'bureau' },
          { filter: 'cfda' },
          { filter: 'state', attribute: 'abbrev' },
          { filter: 'memberid', attribute: 'id' }
        ])
        .map(function (object) {
          return getFilterViewparam(object.filter, object.attribute);
        })
        .tap(function (array) {
          var congress = _.get($scope, 'data.congress.congress');
          if ($scope.data.isMemberAtAward && congress) {
            array.push(`congress:'${congress}'`);
          }
        })
        .reject(_.isEmpty)
        .join(';')
        .value();

      $scope.wmsSource.updateParams(Object.assign({}, $scope.wmsSource.getParams(), {
        'viewparams': viewparams,
        'LAYERS': ($scope.data.isMemberAtAward) ? AT_AWARD_DISTRICTS_LAYER_NAME : CURRENT_DISTRICTS_LAYER_NAME
      }));
    }

    $scope.subcommitteeUpdate = function() {
      var sub = $scope.data.subcommittee;

      if (!_.isEmpty(sub)) {
        Agency.query({ subcommittee: sub }, function(response) {
          $scope.agencies = response ? response : [];
        });
      } else {
        $scope.agencies = [];
      }

      $scope.agencyUpdate();
      $scope.bureauUpdate();
    }

    $scope.agencyUpdate = function() {
      var sub = $scope.data.subcommittee;
      var agency = $scope.data.agency;

      if (!_.isEmpty(sub) && !_.isEmpty(agency)) {
        Bureau.query({ agency: agency, subcommittee: sub }, function(response) {
          $scope.bureaus = response ? response : [];
        });
      } else {
        $scope.bureaus = [];
      }

      $scope.bureauUpdate();
    }

    $scope.bureauUpdate = function() {
      var sub = $scope.data.subcommittee;
      var agency = $scope.data.agency;
      var bureau = $scope.data.bureau;

      if (!_.isEmpty(sub) && !_.isEmpty(agency) && !_.isEmpty(bureau)) {
        Program.query({ bureau: bureau, agency: agency, subcommittee: sub }, function(response) {
          $scope.cfdas = response ? response : [];
        });
      } else {
        $scope.cfdas = [];
      }
    }

    $scope.getCSV = function(endpoint) {
      var getParameters = $httpParamSerializer({
        fiscal_year: $scope.data.fiscal_year,
        subcommittee: $scope.data.subcommittee,
        agency_name: $scope.data.agency,
        bureau_name: $scope.data.bureau,
        cfda: $scope.data.cfda,
        state: _.map($scope.data.state, 'abbrev'),
        memberid: _.map($scope.data.memberid, 'id'),
        congress: _.get($scope, 'data.congress.congress'),
        isMemberAtAward: $scope.data.isMemberAtAward
      });
      var base = `/grantmapper-0.1/form/${endpoint}?`;
      return base + getParameters;
    };

    $scope.memberAutoComplete = function(val, select) {
      var promise = Promise.resolve([]);

      if (!_.isEmpty(val) || !_.isEmpty($scope.data.state)) {
        promise = $http.get('/grantmapper-0.1/form/members', {
          params: {
            partial: val,
            state: _.map($scope.data.state, 'abbrev'),
            congress: _.get($scope, 'data.congress.congress')
          }
        }).then(function(response) {
          return response.data;
        });
      }

      promise.then(function(data) {
        $scope.members = data;
      }).finally(function() {
        select.refreshItems();
      })
    };

    function getFilterViewparam(filter, attribute) {
      var list = $scope.data[filter];
      if(!_.isEmpty(list) && !(list.length === 1 && list[0] === ALL_VALUE)) {
        var listData = getListAsViewparamsValue(list, attribute);
        return `${filter}:${listData}`;
      } else {
        return '';
      }
    }

    function getListAsViewparamsValue(list, attribute) {
      return _.chain(list)
        .map(function (item) {
          var value = (attribute) ? item[attribute] : item;
          return `'${value}'`;
        })
        .join('\\,')
        .value();
    }

    function getDistrictGrantsSource() {
      return new ol.source.ImageWMS({
        url: GEOSERVER_URL,
        params: {
          FORMAT: 'image/png',
          VERSION: '1.1.1',
          LAYERS: CURRENT_DISTRICTS_LAYER_NAME,
          STYLES: '',
          viewparams: `fiscal_year:'2017'`
        },
        serverType: 'geoserver',
        crossOrigin: ''
      });
    }

    function getMap(wmsSource) {
      wmsSource.on('imageloadstart', function() {
        $scope.$apply(function () {
          $scope.wmsLoading = true;
        });
      });

      wmsSource.on('imageloadend', function() {
        $scope.$apply(function () {
          $scope.wmsLoading = false;
        });
      });

      var wmsLayer = new ol.layer.Image({
        name: 'district-grants',
        source: wmsSource
      });

      var view = new ol.View({
          center: [0, 0],
          zoom: 2
        });
      var map = new ol.Map({
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          }),
          wmsLayer
        ],
        target: 'map',
        controls: ol.control.defaults({
          attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
            collapsible: false
          })
        }),
        view: view
      });
      // Zooms to continental US using the preselected extent
      map.getView().fit([-14059521.23466218, 2695475.3654484553, -7230331.379551394, 6344884.84389591], map.getSize());

      // Get feature info click handler.
      map.on('singleclick', function(evt) {
        var viewResolution = /** @type {number} */ (view.getResolution());
        var url = wmsSource.getGetFeatureInfoUrl(
            evt.coordinate, viewResolution, 'EPSG:3857',
            {'INFO_FORMAT': 'application/json'});
        if (url) {
          // Tell angular to show the loading spinner.
          $rootScope.$apply(function () {
            $rootScope.featureInfo = null;
            $rootScope.loadingFeatureInfo = true;
          });
          // Make the server request.
          $http.get(url).then(function(result) {
            if (result.data.features.length > 0) {
              $rootScope.featureInfo = result.data.features[0].properties;
            }
          })
          // Turn off the loading spinner.
          .finally(function () {
            $rootScope.loadingFeatureInfo = false;
          });
        }
      });

      // Change the pointer when hovering over a feature.
      map.on('pointermove', function(evt) {
        if (evt.dragging) {
          return;
        }
        var pixel = map.getEventPixel(evt.originalEvent);
        var hit = map.forEachLayerAtPixel(pixel, function(layer) {
          return layer.get('name') === wmsLayer.get('name');
        });
        map.getTargetElement().style.cursor = hit ? 'pointer' : '';
      });

      return map;
    }

    function initializeRightPanel() {
      Congress.query(function(response) {
        if (!_.isEmpty(response)) {
          _.remove(response, function (congress) {
            return congress.congress < 100;
          });
          $scope.data.congress = response[0];
          $scope.congresses = response;
        } else {
          $scope.congresses = [];
        }
      });
      State.query(function(response) {
        $scope.states = response ? response : [];
        $scope.data.state = _.slice($scope.state, 0, 1);
      });
    }

    function initializeBottomPanel() {
      Year.query(function(response) {
        $scope.fiscal_years = response ? response : [];
        $scope.data.fiscal_year = DEFAULT_FISCAL_YEAR;
      });
      Subcommittee.query(function(response) {
        $scope.subcommittees = response ? response : [];
      });
    }

  };

  angular.module("myApp").controller("AppController", AppController);
}(angular));
