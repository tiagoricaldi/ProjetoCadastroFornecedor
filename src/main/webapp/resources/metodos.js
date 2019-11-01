/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module("Cadastro de Fornecedores", [])
        .value('urlBase', 'http://localhost:8080/teste/rest/')
        .controller("CrudController", function ($http, urlBase) {
            var self = this;
           
            self.usuario = 'Tiago Ricaldi';
            self.crudcontroller = [];
            self.fornecedor = undefined;

            self.novo = function () {
                self.fornecedor = {};
            };

            self.salvar = function () {
                var metodo = 'POST';
                if (self.fornecedor.id_fornecedor) {
                    metodo = 'PUT';
                }

                $http({
                    method: metodo,
                    url: urlBase + 'crudcontroller/',
                    data: self.fornecedor
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.alterar = function (fornecedor) {
                self.fornecedor = fornecedor;
            };

            self.deletar = function (fornecedor) {
                self.fornecedor = fornecedor;

                $http({
                    method: 'DELETE',
                    url: urlBase + 'crudcontroller/' + self.fornecedor.id_fornecedor + '/'
                }).then(function successCallback(response) {
                    self.atualizarTabela();
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };       

            self.ocorreuErro = function () {
                alert("Ocorreu um erro inesperado!");
            };

            self.atualizarTabela = function () {
                $http({
                    method: 'GET',
                    url: urlBase + 'crudcontroller/'
                }).then(function successCallback(response) {
                    self.crudcontroller = response.data;
                    self.fornecedor = undefined;
                }, function errorCallback(response) {
                    self.ocorreuErro();
                });
            };

            self.activate = function (){
                self.atualizarTabela();
            };
            self.activate();
        });

