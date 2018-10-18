import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;


class TestMyPoint {
	
	private MyPoint point1;
	private MyPoint point2;
	private MyPoint point3;
	private MyPoint point4;
	
	@Mock
	private Random randX;
	
	@Mock
	private Random randY;
	
	@Mock
	private ITranslation point;

	@BeforeEach
	void setUp() throws Exception {
		point1 = new MyPoint();
		point2 = new MyPoint(2, 3);
		point3 = new MyPoint(null);
		point4 = new MyPoint(new MyPoint(5,7));
		randX = new Random();
		randY = new Random();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructor1() {
		assertEquals(point1.getX(), 0);
		assertEquals(point1.getY(), 0);
	}
	
	@Test
	void testConstructor2() {
		assertEquals(point2.getX(), 2);
		assertEquals(point2.getY(), 3);
	}
	
	@Test
	void testAccesseurX() {
		point1.setX(4);
		assertEquals(point1.getX(), 4);
	}
	
	@Test
	void testAccesseurY() {
		point1.setY(9);
		assertEquals(point1.getY(), 9);
	}
	
	@Test
	void testAccesseurXNan() {
		point1.setX(Double.NaN);
		assertTrue(Double.isNaN(point1.getX()));
	}
	
	@Test
	void testAccesseurYNan() {
		point1.setY(Double.NaN);
		assertTrue(Double.isNaN(point1.getY()));
	}
	
	@Test
	void testConstructor3Null() {
		assertEquals(point3.getX(), 0);
		assertEquals(point3.getY(), 0);
	}
	
	@Test
	void testConstructor3NotNull() {
		assertEquals(point4.getX(), 5);
		assertEquals(point4.getY(), 7);
	}
	
	@Test
	void testScale() {
		point2 = point2.scale(4);
		assertEquals(point2.getX(), 8);
		assertEquals(point2.getY(), 12);
	}
	
	@Test
	void testHorizontalSymmetryNotNull() {
		point2 = point2.horizontalSymmetry(point1);
		assertEquals(point2.getX(), -2);
	}
	
	@Test
	void testHorizontalSymmetryNotNull2() {
		point2 = point2.horizontalSymmetry(point2);
		assertEquals(point2.getX(), 2);
	}
	
	@Test
	void testHorizontalSymmetryNull() {
		assertThrows(IllegalArgumentException.class, () -> { point2 = point2.horizontalSymmetry(null); });
	}
	
	@Test
	void testRotatePointNull() {
		assertEquals(null, point1.rotatePoint(null, 5));
	}
	
	@Test
	void testCentralSymmetryNull() {
		assertThrows(IllegalArgumentException.class, () -> {point2 = point2.centralSymmetry(null); });
	}
	
	@Test
	void testCentralSymmetry() {
		point2 = point2.centralSymmetry(point1);
		assertEquals(-2, point2.getX());
		assertEquals(-3, point2.getY());
	}
	
	@Test
	void testCentralSymmetry2() {
		point2 = point2.centralSymmetry(point2);
		assertEquals(2, point2.getX());
		assertEquals(3, point2.getY());
	}
	
	@Test
	void testGetMiddlePointNull() {
		point2 = point2.getMiddlePoint(null);
		assertEquals(2, point2.getX());
		assertEquals(3, point2.getY());
	}
	
	@Test
	void testGetMiddlePoint() {
		point2 = point2.getMiddlePoint(point1);
		assertEquals(1, point2.getX());
		assertEquals(1.5, point2.getY());
	}
	
	@Test
	void testSetPoint() {
		when(randX.nextInt()).thenReturn(2);
		when(randY.nextInt()).thenReturn(-5);
		point1.setPoint(randX, randY);
		assertEquals(2, point1.getX());
		assertEquals(-5, point1.getY());
	}
	

}
