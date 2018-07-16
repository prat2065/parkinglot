package com.gojek.parkinglot;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gojek.parkinglot.ParkingLotHelper;

@Test
public class ParkingLotHelperTest {

	private Set<Long> set;
	
	@BeforeMethod
	public void setUp() {
		this.set = Collections.synchronizedSet(new HashSet<Long>());
	}
	
	public void testServiceForMultiThreadedEnv() throws InterruptedException {

		Thread thread1 = new Thread(new Runnable() {

			public void run() {
				for(int i=0;i<1000;i++) {
					set.add(ParkingLotHelper.getUniqueToken());
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {

			public void run() {
				for(int i=0;i<1000;i++) {
					set.add(ParkingLotHelper.getUniqueToken());
				}

			}
		});

		Thread thread3 = new Thread(new Runnable() {

			public void run() {
				for(int i=0;i<1000;i++) {
					set.add(ParkingLotHelper.getUniqueToken());
				}

			}
		});
		thread1.start();
		thread2.start();
		thread3.start();
		thread1.join();
		thread2.join();
		thread3.join();
		System.out.println(ParkingLotHelper.tokenNo);
		Assert.assertEquals(set.size(), 3000);

	}

}
