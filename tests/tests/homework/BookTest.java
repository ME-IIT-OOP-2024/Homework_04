package tests.homework;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import homework.Book;


public class BookTest {
	protected static final String author = "J.K. Rowling";
	protected static final String title = "Harry Potter";
	protected static final int yearOfPub = 2008;
	protected static final int price = 3500;

	protected Book createValidBook()
	{
		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(title);
		book.setYearOfPublication(yearOfPub);
		book.setPrice(price);
		
		return book;
	}
	
	@Test
	@DisplayName("A setPrice-nak az 1000 feletti �rt�ket �rintetlen�l kell hagynia!")
	public void setPrice_Above999_ShouldNotBeChanged()
	{
		Book book = createValidBook();
		book.setPrice(price);

		assertEquals(price, book.getPrice());
	}
	
	@Test
	@DisplayName("Az increasePrice-nak pozit�v �rt�kre m�dos�tania kell az �rat!")
	public void increasePrice_ByAPositiveValue_ShouldChangePrice()
	{
		Book book = createValidBook();
		int originalPrice = 3500;
		book.setPrice(originalPrice);
		int expectedIncreasedPrice = 4025;
		book.increasePrice(15);

		assertEquals(expectedIncreasedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -10})
	@DisplayName("Az increasePrice-nak nem pozit�v �rt�kre nem szabad m�dos�tania az �rat!")
	public void increasePrice_By0OrNegativeValue_ShouldNotChangePrice(int priceInc)
	{
		Book book = createValidBook();
		int originalPrice = 3500;
		book.setPrice(originalPrice);
		int expectedIncreasedPrice = book.getPrice();
		book.increasePrice(priceInc);

		assertEquals(expectedIncreasedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@CsvSource({"1004,1104", "1005,1106", "1006,1107"})
	@DisplayName("Az increasePrice-nak t�rt eredm�ny eset�n kerek�tenie kell a matematikai szab�lyoknak megfelel�en!")
	public void increasePrice_FractionalResult_ShouldBeRoundedAccordingToArithmeticRules(int originalPrice, int expectedPrice)
	{
		Book book = createValidBook();
		book.setPrice(originalPrice);
		book.increasePrice(10);

		assertEquals(expectedPrice, book.getPrice());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-1001, -1000, -999, 0, 999})
	@DisplayName("Az setPrice-nak az 1000 alatti �rt�keket 1000-re kell �ll�tania")
	public void setPrice_Below1000_ShouldBeCorrectedTo1000(int price)
	{
		Book book = createValidBook();
		book.setPrice(price);
		assertEquals(1000, book.getPrice());
	}
	
	@Test
	@DisplayName("Az displayInfo kimenet�nek tartalmaznia kell a c�met")
	public void displayInfo_ResultShouldContainTitle()
	{
		Book book = createValidBook();
		String result = book.displayInfo();
		assertTrue(result.contains(title),
				"A displayInfo �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
	
	@Test
	@DisplayName("Az displayInfo kimenet�nek tartalmaznia kell a szerz�t")
	public void displayInfo_ResultShouldContainAuthor()
	{
		Book book = createValidBook();
		String result = book.displayInfo();
		assertTrue(result.contains(author),
				"A displayInfo �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
	
	@Test
	@DisplayName("Az displayInfo kimenet�nek tartalmaznia kell a publik�ci� �v�t")
	public void displayInfo_ResultShouldContainYearOfPublication()
	{
		Book book = createValidBook();
		String result = book.displayInfo();
		assertTrue(result.contains(Integer.toString(yearOfPub)),
				"A displayInfo �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
	
	@Test
	@DisplayName("Az displayInfo kimenet�nek tartalmaznia kell az �rat")
	public void displayInfo_ResultShouldContainPrice()
	{
		Book book = createValidBook();
		String result = book.displayInfo();
		assertTrue(result.contains(Integer.toString(price)),
				"A displayInfo �ltal el��ll�tott string nem tartalmazza az �rat!");
	}
}
