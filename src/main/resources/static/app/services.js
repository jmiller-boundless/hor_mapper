(function(angular) {

  var SubFactory = function($resource) {
    return $resource('/grantmapper-0.1/form/subcommittees');
  };
  SubFactory.$inject = ['$resource'];
  angular.module("myApp").factory("Subcommittee", SubFactory);

  var AgencyFactory = function($resource) {
   return $resource('/grantmapper-0.1/form/agencies');
  };
  AgencyFactory.$inject = ['$resource'];
  angular.module("myApp").factory("Agency", AgencyFactory);

  var BureauFactory = function($resource) {
    return $resource('/grantmapper-0.1/form/bureaus');
  };
  BureauFactory.$inject = ['$resource'];
  angular.module("myApp").factory("Bureau", BureauFactory);

  var ProgramFactory = function($resource) {
   return $resource('/grantmapper-0.1/form/programs');
  };
  ProgramFactory.$inject = ['$resource'];
  angular.module("myApp").factory("Program", ProgramFactory);

  var CongressFactory = function($resource) {
    return $resource('/grantmapper-0.1/form/congresses');
  };
  CongressFactory.$inject = ['$resource'];
  angular.module("myApp").factory("Congress", CongressFactory);

  var StateFactory = function($resource) {
    return $resource('/grantmapper-0.1/form/states');
  };
  StateFactory.$inject = ['$resource'];
  angular.module("myApp").factory("State", StateFactory);

  var YearFactory = function($resource) {
    return $resource('/grantmapper-0.1/form/years');
  };
  YearFactory.$inject = ['$resource'];
  angular.module("myApp").factory("Year", YearFactory);

}(angular));
