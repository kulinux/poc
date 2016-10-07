'use strict';

/**
 * @ngdoc function
 * @name weightApp.controller:WeightCtrl
 * @description
 * # WeightCtrl
 * Controller of the weightApp
 */
angular.module('weightApp')
  .controller('WeightCtrl', ['$scope', '$resource', function ($scope, $resource) {
    var WeightRsc = $resource('http://localhost:8888/server/weight/:id', {id: '@id'});

    var reload = function () {
      $scope.weights = WeightRsc.query(function () {
      });
    }

    reload();

    $scope.weight = {};


    $scope.update = function (weight) {
      console.log(weight);
      var weightRsc = new WeightRsc();
      weightRsc.user = weight.user;
      weightRsc.weight = parseInt(weight.weight);
      weightRsc.status = weight.status;
      weightRsc.$save(function() {
        reload();
      });
    };

    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }]);
