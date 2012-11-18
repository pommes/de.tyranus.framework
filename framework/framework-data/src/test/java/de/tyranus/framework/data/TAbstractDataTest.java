package de.tyranus.framework.data;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author tim
 * 
 */
@RunWith(value = Parameterized.class)
public class TAbstractDataTest {

	private final Person person1;
	private final Person person2;

	public TAbstractDataTest(Person person1, Person person2) {
		this.person1 = person1;
		this.person2 = person2;
	}

	@Parameters
	public static Collection<TAbstractData[]> data() {
		final TestData data1 = new TestData();
		final TestData data2 = new TestData();
		final TestDataKey dataKey1 = new TestDataKey();
		final TestDataKey dataKey2 = new TestDataKey();

		final TAbstractData[][] data = new TAbstractData[][] { { data1, data2 }, { dataKey1, dataKey2 } };
		return Arrays.asList(data);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.tyranus.framework.data.TAbstractData#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		// Uninitialized
		//assertEquals(7, person1.hashCode());

		// Initialized
		final int age = 29;
		final String name = "Tim";
		person1.setName(name);
		person1.setAge(age);
		assertEquals((31 * (31 * 7 + age) + name.hashCode()), person1.hashCode());

	}

	/**
	 * Test method for
	 * {@link de.tyranus.framework.data.TAbstractData#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		final String name = "Tim";
		final int age = 29;

		// Uninitialized
		//assertEquals(person1, person2);

		// Different key size
		person1.setName(name);
		person1.setAge(age);
		person2.setName(name);
		assertFalse(person1 == person2);

		// Initialized - equal
		person2.setAge(age);
		assertEquals(person1, person2);

		// Initialized - not equal
		person2.setAge(28);
		assertFalse(person1 == person2);

		// One entry null
		person2.setAge(age);
		person2.setName(null);
		assertFalse(person1 == person2);
	}

	/**
	 * Test method for
	 * {@link de.tyranus.framework.data.TAbstractData#toString()}.
	 */
	@Test
	public void testToString() {
		// Uninitialized
		String expectedClassname = null;
		if (person1 instanceof TestData) {
			expectedClassname = "TestData";
		}
		else if (person1 instanceof TestDataKey) {
			expectedClassname = "TestDataKey";
		}
		assertEquals(expectedClassname + "[]", person1.toString());

		// Initialized - equal
		final String name = "Tim";
		final int age = 29;
		person1.setAge(age);
		person1.setName(name);
		assertEquals(expectedClassname + "[AGE='" + age + "', NAME='" + name + "']", person1.toString());
	}

	static interface Person {

		public abstract void setName(String name);

		public abstract String getName();

		public abstract void setAge(int age);

		public abstract int getAge();

	}

	static class TestData extends TAbstractData implements Person {
		private final static String NAME = "NAME";
		private final static String AGE = "AGE";

		public void setName(String name) {
			setString(NAME, name);
		}

		public String getName() {
			return getString(NAME);
		}

		public void setAge(int age) {
			setInt(AGE, age);
		}

		public int getAge() {
			return getInt(AGE);
		}
	}

	static class TestDataKey extends TAbstractDataKey implements Person {

		public void setName(String name) {
			setString(key(), name);
		}

		public String getName() {
			return getString(key());
		}

		public void setAge(int age) {
			setInt(key(), age);
		}

		public int getAge() {
			return getInt(key());
		}
	}

}
