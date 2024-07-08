package org.stablex.utils;

import lombok.Data;
import org.stablex.pages.HomePage;

@Data
public class Pages {

	private HomePage homePage;

	public Pages() {
		homePage = new HomePage();
	}

}
