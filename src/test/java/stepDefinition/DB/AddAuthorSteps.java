package stepDefinition.DB;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import utils.DatabaseManager;
import DB.SQLUtils;

import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.*;

public class AddAuthorSteps {
    private Connection connection;

    @Given("user is connected to the database")
    public void i_am_connected_to_the_database() {
        connection = DatabaseManager.getConnection();
    }

    @When("user add a new author with name {string} and bio {string}")
    public void i_add_a_new_author_with_name_and_bio(String name, String bio) {
        try {
            SQLUtils.insertAuthor(connection, name, bio);
        } catch (SQLException e) {
            fail("Failed to insert author: " + e.getMessage());
        }
    }

    @Then("the author {string} should be in the Authors table")
    public void the_author_should_be_in_the_authors_table(String name) {
        try {
            boolean isPresent = SQLUtils.isAuthorPresent(connection, name);
            assertTrue(isPresent);
        } catch (SQLException e) {
            fail("Failed to verify author: " + e.getMessage());
        }
    }

    @And("added author {string} is deleted")
    public void addedAuthorIsDeleted(String name) {
        try {
            SQLUtils.deleteAuthor(connection, name);
        } catch (SQLException e) {
            fail("Failed to delete author: " + e.getMessage());
        }
    }
}