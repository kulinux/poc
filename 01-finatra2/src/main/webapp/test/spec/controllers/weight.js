'use strict';

describe('Controller: WeightCtrl', function () {

  // load the controller's module
  beforeEach(module('weightApp'));

  var WeightCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    WeightCtrl = $controller('WeightCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(WeightCtrl.awesomeThings.length).toBe(3);
  });
});
