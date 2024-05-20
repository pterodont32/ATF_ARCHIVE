package stepDefinition.DB;

import DB.BookSqlUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DatabaseManager;
import DB.AuthorSqlUtils;
import utils.ScenarioContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorSteps {
    private Connection connection;
    private  static final  ScenarioContext scenarioContext = new ScenarioContext();
    private static final Logger log = LogManager.getLogger(AuthorSteps.class);

    @Given("user is connected to the database")
    public void i_am_connected_to_the_database() {
        connection = DatabaseManager.getConnection();
    }

    @When("user add a new author with name {string} and bio {string}")
    public void i_add_a_new_author_with_name_and_bio(String name, String bio) {
        try {
            AuthorSqlUtils.insertAuthor(connection, name, bio);
        } catch (SQLException e) {
            fail("Failed to insert author: " + e.getMessage());
        }
    }

    @Then("the author {string} should be in the Authors table")
    public void the_author_should_be_in_the_authors_table(String name) {
        try {
            boolean isPresent = AuthorSqlUtils.isAuthorPresent(connection, name);
            assertTrue(isPresent);
        } catch (SQLException e) {
            fail("Failed to verify author: " + e.getMessage());
        }
    }

    @And("added author {string} is deleted")
    public void addedAuthorIsDeleted(String name) {
        try {
            AuthorSqlUtils.deleteAuthor(connection, name);
        } catch (SQLException e) {
            fail("Failed to delete author: " + e.getMessage());
        }
    }

    @When("user retrieves all books written by {string}")
    public void user_retrieves_all_books_written_by(String authorName) {
        try {
            List<String> retrievedBookTitles = BookSqlUtils.getBooksByAuthor(connection, authorName);
            scenarioContext.setContext("retrievedBookTitles", retrievedBookTitles);
        } catch (SQLException e) {
            fail("Failed to retrieve books: " + e.getMessage());
        }
    }

    @Then("the retrieved book titles should be:")
    public void the_retrieved_book_titles_should_be(DataTable expectedBookTitlesTable) {
        List<String> expectedBookTitles = expectedBookTitlesTable.asList();
        List<String> retrievedBookTitles = scenarioContext.getContext("retrievedBookTitles", List.class);
        assertEquals(expectedBookTitles, retrievedBookTitles);
        log.info("Verified retrieved book titles: {}", retrievedBookTitles);
    }


}