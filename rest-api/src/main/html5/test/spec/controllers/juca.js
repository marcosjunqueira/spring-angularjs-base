'use strict';

describe('Controller: JucaCtrl', function () {

  // load the controller's module
  beforeEach(module('valecardAngularApp'));

  var JucaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    JucaCtrl = $controller('JucaCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
