(function(angular) {
	  var AgencyFactory = function($resource) {
	    return $resource('/grantmapper-0.1/form/agencies/:subcommittee');
	  };
	  AgencyFactory.$inject = ['$resource'];
	  angular.module("myApp.services").factory("Agency", AgencyFactory);
	  
	  var BureauFactory = function($resource) {
        return $resource('/grantmapper-0.1/form/bureaus/:subcommittee/:agency');
      };
      BureauFactory.$inject = ['$resource'];
      angular.module("myApp.services").factory("Bureau", BureauFactory);
      
	  var ProgramFactory = function($resource) {
	        return $resource('/grantmapper-0.1/form/programs/:subcommittee/:agency/:bureau');
	      };
	      ProgramFactory.$inject = ['$resource'];
	      angular.module("myApp.services").factory("Program", ProgramFactory);
      
      var SubFactory = function($resource) {
          return $resource('/grantmapper-0.1/form/subcommittees');
        };
        SubFactory.$inject = ['$resource'];
        angular.module("myApp.services").factory("Subcommittee", SubFactory);
        
       var StateFactory = function($resource) {
            return $resource('/grantmapper-0.1/form/states');
          };
          StateFactory.$inject = ['$resource'];
          angular.module("myApp.services").factory("State", StateFactory); 
          
      var CongressFactory = function($resource) {
          return $resource('/grantmapper-0.1/form/congresses');
        };
        CongressFactory.$inject = ['$resource'];
        angular.module("myApp.services").factory("Congress", CongressFactory); 
      
        var YearFactory = function($resource) {
            return $resource('/grantmapper-0.1/form/years');
          };
          YearFactory.$inject = ['$resource'];
          angular.module("myApp.services").factory("Year", YearFactory);
          

}(angular));