package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private  TorpedoStore pm;

  private TorpedoStore sec;

  @BeforeEach
  public void init(){

     pm = mock(TorpedoStore.class);

     sec=  mock(TorpedoStore.class);


    this.ship = new GT4500(pm,sec);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(pm.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn((true));
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pm,times(1)).fire(1);
    verify(sec,times(0)).fire(1);


  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(pm.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn((true));
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(pm,times(1)).fire(1);
    verify(sec,times(1)).fire(1);
  }

  @Test
  public void FirstShoot_SecondNeedtoShoot(){
    //Arrange
    when(pm.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn((true));

    //act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    verify(pm,times(1)).fire(1);
    verify(sec,times(1)).fire(1);
  }

  @Test
  public void FirstShoot_SecondNeedtoShoot_FirstShootAgain(){
    //Arrange
    when(pm.fire(1)).thenReturn(true);
    when(sec.fire(1)).thenReturn((true));

    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    verify(pm,times(2)).fire(1);
    verify(sec,times(1)).fire(1);
  }

  @Test
  public void BothStoreIsEmptyFireAll(){
    //Arrange
    when(pm.isEmpty()).thenReturn(true);
    when(sec.isEmpty()).thenReturn(true);

    //Act
    boolean result =this.ship.fireTorpedo(FiringMode.ALL);

    //Assert
    assertEquals(false,result);
    verify(pm,times(0)).fire(1);
    verify(sec,times(0)).fire(1);
  }

  @Test
  public void BothStoreIsEmptyFireSinglePM(){
    //Arrange
    when(pm.isEmpty()).thenReturn(true);
    when(sec.isEmpty()).thenReturn(true);

    //Act
    boolean result = this.ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(false,result);

  }

  @Test
  public void BothStoreIsEmptyFireSingleSec(){
    //Arrange
    when(pm.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(true);

    //Act
    this.ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = this.ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(false,result);

  }


  @Test
  public void BothStoreIsEmptyFireSingleSec2(){
    //Arrange
    when(pm.isEmpty()).thenReturn(true);
    when(sec.isEmpty()).thenReturn(false);

    //Act
    this.ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = this.ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(false,result);

  }

  @Test
  public void BothStoreIsEmptyFireSingleSec3(){
    //Arrange
    when(pm.isEmpty()).thenReturn(false);
    when(sec.isEmpty()).thenReturn(true);

    //Act
    this.ship.fireTorpedo(FiringMode.SINGLE);
    this.ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = this.ship.fireTorpedo(FiringMode.SINGLE);

    //Assert
    assertEquals(false,result);

  }

}
