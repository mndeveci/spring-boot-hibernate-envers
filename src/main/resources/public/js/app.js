var myModuleConfig = function($stateProvider, $urlRouterProvider){

    $urlRouterProvider.otherwise("/");

    $stateProvider.state("home", {
        url: "/",
        templateUrl: "/views/home.html"
    }).state("viewCities", {
        url: "/view-cities/",
        templateUrl: "/views/view-cities.html",
        controller: "viewCitiesController"
    }).state("admin", {
        url: "/admin/",
        templateUrl: "/views/admin/index.html"
    }).state("admin.cities", {
        url: "cities/",
        templateUrl: "/views/admin/cities/index.html",
        controller: "adminCitiesListController"
    }).state("admin.cities.add", {
        url: "add/",
        templateUrl: "/views/admin/cities/add.html",
        controller: "adminCitiesAddController"
    }).state("admin.cities.edit", {
        url: "edit/:cityCode",
        templateUrl: "/views/admin/cities/edit.html",
        controller: "adminCitiesEditController"
    });
};
myModuleConfig.$inject = ["$stateProvider", "$urlRouterProvider"];

// Services

var citiesService = function($resource){
    return $resource("/api/cities/:id", {
        id: "@cityCode"
    }, {
        update: {
            method: "PUT"
        }
    });
};
citiesService.$inject = ["$resource"];






// Controllers

var viewCitiesController = function($scope, citiesService){
    $scope.cities = citiesService.query();
    $scope.filterText = "";
};
viewCitiesController.$inject = ["$scope", "citiesService"];

var adminCitiesListController = function($scope, citiesService, $state){
    $scope.cities = citiesService.query();

    $scope.delete = function(city){
      if(confirm(city.name + " şehrini silmek istediğinize emin misiniz?")) {
          city.$delete(function(){
              $state.reload();
          });
      }
    };
};
adminCitiesListController.$inject = ["$scope", "citiesService", "$state"];


var adminCitiesAddController = function($scope, citiesService, $state){
    $scope.city = new citiesService();

    $scope.save = function(){
        $scope.city.$save(function(){
            $state.go("admin.cities", {}, {reload: true});
        });
    };
};
adminCitiesAddController.$inject = ["$scope", "citiesService", "$state"];

var adminCitiesEditController = function($scope, citiesService, $state, $stateParams, $http){
    $scope.city = citiesService.get({id: $stateParams.cityCode});
    $scope.edits = [];

    $http.get("/api/cities/history/revisions/" + $stateParams.cityCode)
        .success(function(data){
            $scope.edits = data;
        });

    $scope.update = function(){
        $scope.city.$update(function(){
            $state.go("admin.cities", {}, {reload: true});
        });
    };
};
adminCitiesEditController.$inject = ["$scope", "citiesService", "$state", "$stateParams", "$http"];



// Init module
angular.module("myFirstAngularModule", ["ui.router", "ngResource"])
    .config(myModuleConfig)
    .factory("citiesService", citiesService)
    .controller("viewCitiesController", viewCitiesController)
    .controller("adminCitiesListController", adminCitiesListController)
    .controller("adminCitiesAddController", adminCitiesAddController)
    .controller("adminCitiesEditController", adminCitiesEditController);