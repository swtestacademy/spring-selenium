package com.udemy.spring.springselenium.page.google;

import com.udemy.spring.springselenium.kelvin.annotation.PageFragment;
import com.udemy.spring.springselenium.page.Base;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageFragment
public class SearchResult extends Base {

    @FindBy(css = "div.g")
    private List<WebElement> results;

    public int getCount(){
        return this.results.size();
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> !this.results.isEmpty());
    }

}
