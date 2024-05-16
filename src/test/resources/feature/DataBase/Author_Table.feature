@DB
Feature: Check Author Table

Background:
 Given user is connected to the database

 Scenario: Add a new author to the Authors table
  When user add a new author with name "Arthur Conan Doyle" and bio "British writer and physician."
  Then the author "Arthur Conan Doyle" should be in the Authors table
  And added author "Arthur Conan Doyle" is deleted


