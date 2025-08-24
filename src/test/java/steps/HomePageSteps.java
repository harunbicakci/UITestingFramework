package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.HomePage;
import pages.MainPage;
import pages.ProductPage;
import testBase.TestData;
import utility.CommonMethods;

import java.util.List;

public class HomePageSteps extends CommonMethods{

    HomePage homePage = new HomePage();

    @When("user enters {string} in the search box")
    public void user_enters_in_the_search_box(String productName){
        waitForVisibility(homePage.allProductsContainer);
        sendText(homePage.searchBoxInput, productName.toLowerCase());
        click(homePage.searchButton);
        wait(3);
    }

    @Then("all products across all pages contain {string} in their name")
    public void all_products_across_all_pages_contain_in_their_name(String productName){
        List<String> names = homePage.getAllProductNamesAcrossPages();
        System.out.println("List of Names size is ======> " + names.size());
        System.out.println("List of Names are ========> " + names);
        for(String name : names){
            Assert.assertTrue("Product name does not contain keyword => "+ name, name.contains(productName));
        }
    }

    @When("user selects sorting by {string}")
    public void user_selects_sorting_by(String sortingType){
        waitForVisibility(homePage.allProductsContainer);
        click(homePage.sortDropDown);
        click(homePage.selectSortingfromDropdown(sortingType));
    }


    @Then("products across all pages are sorted by name ascending")
    public void products_across_all_pages_are_sorted_by_name_ascending(){
        wait(3);
        Assert.assertTrue("Products not sorted by name ascending", homePage.areProductsSortedByNameAsc());
    }

}
