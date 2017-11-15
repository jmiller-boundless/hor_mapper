(function(angular) {
  var AppController = function($rootScope, $scope, $http, $httpParamSerializer, Agency, Bureau,Subcommittee,State,Congress, Year, Program) {
    $scope.data = {};
	  var format = 'image/png';
	 //var viewparam = "fy:'2015';cfda:'11.300'\\,'11.302'\\,'16.710'";
	  var viewparam = "fy:'2015'";
	  var paramv = {'FORMAT': 'image/png',
              'VERSION': '1.1.1',
              LAYERS: 'opengeo:mapview',
              STYLES: '',
              viewparams: viewparam
        };

	  var wmsSource = new ol.source.ImageWMS({
		  url: 'http://34.226.38.202:8080/geoserver/opengeo/wms',
          params: paramv,
		  serverType: 'geoserver',
		  crossOrigin: ''
		});

		var wmsLayer = new ol.layer.Image({
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

	  map.on('singleclick', function(evt) {
      // TODO: handle getFeatureInfo
		  //document.getElementById('info').innerHTML = '';
		  var viewResolution = /** @type {number} */ (view.getResolution());
		  var url = wmsSource.getGetFeatureInfoUrl(
		      evt.coordinate, viewResolution, 'EPSG:3857',
		      {'INFO_FORMAT': 'application/json'});
		  if (url) {
        $rootScope.$apply(function () {
          $rootScope.featureInfo = null;
          $rootScope.loadingFeatureInfo = true;
        });
		    $http.get(url).then(function(result) {
          $rootScope.loadingFeatureInfo = false;
          if (result.data.features.length > 0) {
            $rootScope.featureInfo = result.data.features[0].properties;
          }
        });
		  }
		});
	  map.on('pointermove', function(evt) {
		  if (evt.dragging) {
		    return;
		  }
		  var pixel = map.getEventPixel(evt.originalEvent);
		  var hit = map.forEachLayerAtPixel(pixel, function(layer) {
		    return true;
		  });
		  map.getTargetElement().style.cursor = hit ? 'pointer' : '';
		});

	  $scope.map=map;

/*    Agency.query({subcommittee:'AR'},function(response) {
      $scope.agencies = response ? response : [];
    });
    AppController.$inject = ['$scope', 'Agency'];

    Bureau.query(function(response) {
        $scope.bureaus = response ? response : [];
      });
    AppController.$inject = ['$scope', 'Bureau'];
    */
    Subcommittee.query(function(response) {
        $scope.subcommittees = response ? response : [];
      });
    AppController.$inject = ['$scope', 'Subcommittee'];
    State.query(function(response) {
        $scope.states = response ? response : [];
        $scope.stateSelect = $scope.states[0];
      });
    AppController.$inject = ['$scope', 'State'];
    Congress.query(function(response) {
        if (response) {
          _.remove(response, function (congress) {
            return congress.congress < 100;
          });
        }
        $scope.cs = response[0];
        $scope.congresses = response ? response : [];
      });
    AppController.$inject = ['$scope', 'Congress'];
    Year.query(function(response) {
        $scope.years = response ? response : [];
      });
    AppController.$inject = ['$scope', 'Year'];

    arrayToEscapedComma = function(val){

    };
    wmsSource.on('imageloadstart', function() {

    });

    wmsSource.on('imageloadend', function() {
      $rootScope.$apply(function () {
        $rootScope.wmsLoading = false;
      });
    });
    $scope.buildParam = function(){
      $rootScope.wmsLoading = true;
      //"fy:2013;cfda:'11.300'\\,'11.302'\\,'16.710'"
      var fy = "";
      if($scope.data.year &&
        $scope.data.year.length > 0){
        fy = "fy:" + arrayToEscapedComma($scope.data.year) + ";";
      }

      var agency="";
      if($scope.data.agencySelect &&
        $scope.data.agencySelect.length > 0 &&
        !($scope.data.agencySelect.length === 1 && $scope.data.agencySelect[0] === "unspecified")) {
        agency = "agencycode:"+arrayToEscapedComma($scope.data.agencySelect) + ";";
      }
      var bureau="";
      if($scope.data.bureauSelect &&
        $scope.data.bureauSelect.length > 0 &&
        !($scope.data.bureauSelect.length === 1 && $scope.data.bureauSelect[0] === "unspecified")) {
        bureau = "bureaucode:"+arrayToEscapedComma($scope.data.bureauSelect) + ";";
      }

      var cfda="";
      if($scope.data.programSelect &&
        $scope.data.programSelect.length > 0 &&
        !($scope.data.programSelect.length === 1 && $scope.data.programSelect[0] === "unspecified")) {
        cfda = "cfda:"+arrayToEscapedComma($scope.data.programSelect) + ";";
      }

      var subcommittee="";
      if($scope.data.subcommitteeSelect &&
        $scope.data.subcommitteeSelect.length > 0 &&
        !($scope.data.subcommitteeSelect.length === 1 && $scope.data.subcommitteeSelect[0] === "unspecified")) {
        subcommittee = "subcommittee:"+arrayToEscapedComma($scope.data.subcommitteeSelect)+";";
      }

      var paramout = fy+subcommittee+agency+bureau+cfda;
      paramv.viewparams = paramout;
      wmsSource.updateParams(paramv);

    }

    $scope.buildCSVReq = function(){
    	var base = "http://34.226.38.202:8080/geoserver/opengeo/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=opengeo%3Acdspending" +
    			"&propertyName=fiscal_year,sum,firstname,middlename,lastname,party,subcommittee,start_date,end_date,statefp,cdfp,state,namelsad,cdsessn&outputformat=csv&viewparams=";
    	var out = base + paramv.viewparams;
    	window.open(out);
    }

    $scope.buildCSV = function(){
    	var base = "/grantmapper-0.1/form/downloadCSV/";
    	var out = base + $scope.data.year;
    	window.open(out);
    }

    $scope.getFilteredCsv = function() {
      var getParameters = $httpParamSerializer({
        fiscal_year: $scope.data.year,
        subcommittee: $scope.data.subcommitteeSelect,
        agency_name: $scope.data.agencySelect,
        bureau_name: $scope.data.bureauSelect,
        cfda: $scope.data.programSelect,
        state: _.get($scope, 'stateSelect.abbrev')
      });
      var base = "/grantmapper-0.1/form/downloadFilteredCSV?";
      return base + getParameters;
    };

    $scope.subcommitteeUpdate = function(){
    	var sub = $scope.data.subcommitteeSelect;
    	Agency.query({subcommittee:sub},function(response) {
    	      $scope.agencies = response ? response : [];
        });
    }

    $scope.agencyUpdate = function(){
    	var agency = $scope.data.agencySelect;
    	var sub = $scope.data.subcommitteeSelect;
    	Bureau.query({agency:agency,subcommittee:sub},function(response) {
    	      $scope.bureaus = response ? response : [];
        });
    }

    $scope.bureauUpdate = function(){
    	var bureau = $scope.data.bureauSelect;
    	var agency = $scope.data.agencySelect;
    	var sub = $scope.data.subcommitteeSelect;
    	Program.query({bureau:bureau,agency:agency,subcommittee:sub},function(response) {
    	      $scope.programs = response ? response : [];
        });
    }

    arrayToEscapedComma = function(val){
    	var out="";
    	for (i = 0; i < val.length; i++) {

    		out = out + "'"+val[i]+"'";
    		if(i!=val.length-1){
    			out=out+"\\,";
    		}
    	}
    	return out;
    }
    arrayToEscapedCommaNoSingleQuotes = function(val){
    	var out="";
    	for (i = 0; i < val.length; i++) {

    		out = out +val[i];
    		if(i!=val.length-1){
    			out=out+"\\,";
    		}
    	}
    	return out;
    }

    $scope.getLocation = function(val) {
        return $http.get('/grantmapper-0.1/form/members', {
          params: {
            partial: val,
            state: $scope.stateSelect.name,
            congress: $scope.cs.congress
          }
        }).then(function(response){
          return response.data.map(function(item){
            //return item.lastname+','+item.firstname+' '+item.middlename;
        	  return item;
          });
        });
      };

      $scope.getLocation2 = function(val) {
          return $http.get('/grantmapper-0.1/form/programs', {
            params: {
              partial: val
            }
          }).then(function(response){
            return response.data.map(function(item){
              //return item.lastname+','+item.firstname+' '+item.middlename;
          	  return item;
            });
          });
        };

      $scope.ngModelOptionsSelected = function(value) {
        if (arguments.length) {
          _selected = value;
        } else {
          return _selected;
        }
      };

      $scope.modelOptions = {
        debounce: {
          default: 500,
          blur: 250
        },
        getterSetter: true
      };

      $scope.onSelect = function ($item, $model, $label) {
    	    $scope.$item = $item;
    	    var f = new ol.format.GeoJSON();
    	    var geom = f.readGeometry($item.geom,{dataProjection:'EPSG:4326',featureProjection:'EPSG:3857'});
    	    //alert($scope.map.getView());
    	    $scope.map.getView().fit(geom.getExtent(), $scope.map.getSize());
    	};

        $scope.onSelect2 = function ($item, $model, $label) {
    	    $scope.cfda = $item.cfda;
    	    //alert($scope.$item.cfda);
    	};
    	$scope.statezoom = function(){
    		var f = new ol.format.GeoJSON();
          var geom = f.readGeometry($scope.data.stateSelect.geom,{dataProjection:'EPSG:4326',featureProjection:'EPSG:3857'});
    	    //alert($scope.map.getView());
    	    $scope.map.getView().fit(geom.getExtent(), $scope.map.getSize());
    	}

  };

  angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));
