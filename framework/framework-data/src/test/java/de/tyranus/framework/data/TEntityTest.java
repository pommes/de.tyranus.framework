package de.tyranus.framework.data;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author tim
 * 
 */
public class TEntityTest {

	static class TestEntity extends TAbstractData {
		private final static String NAME = "name";
		private final static String AGE = "age";

		public void setName(String name) {
			setStringValue(NAME, name);
		}

		public String getName() {
			return getStringValue(NAME);
		}

		public void setAge(int age) {
			setIntValue(AGE, age);
		}

		public int getAge() {
			return getIntValue(AGE);
		}
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
		TestEntity t = new TestEntity();
		assertEquals(7, t.hashCode());

		// Initialized
		final int age = 29;
		final String name = "Tim";
		t.setName(name);
		t.setAge(age);
		assertEquals((31 * (31 * 7 + age) + name.hashCode()), t.hashCode());

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
		TestEntity t = new TestEntity();
		TestEntity u = new TestEntity();
		assertEquals(t, u);

		// Different key size
		t.setName(name);
		t.setAge(age);
		u.setName(name);
		assertFalse(t == u);

		// Initialized - equal
		u.setAge(age);
		assertEquals(t, u);

		// Initialized - not equal
		u.setAge(28);
		assertFalse(t == u);

		// One entry null
		u.setAge(age);
		u.setName(null);
		assertFalse(t == u);
	}

	/**
	 * Test method for
	 * {@link de.tyranus.framework.data.TAbstractData#toString()}.
	 */
	@Test
	public void testToString() {
		// Uninitialized
		TestEntity t = new TestEntity();
		assertEquals("TestEntity[]", t.toString());

		// Initialized - equal
		final String name = "Tim";
		final int age = 29;
		t.setAge(age);
		t.setName(name);
		assertEquals("TestEntity[age='" + age + "', name='" + name + "']", t.toString());
	}

}
