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
    var WeightRsc = $resource('/server/weight/:id', {id: '@id'});

    $scope.weights = WeightRsc.query(function () {
    });

    $scope.weight = {};


    $scope.update = function (weight) {
      console.log(weight);
      var weightRsc = new WeightRsc();
      weightRsc.user = weight.user;
      weightRsc.weight = parseInt(weight.weight);
      weightRsc.status = weight.status;
      weightRsc.$save(function() {

      });
    };

    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  }]);
