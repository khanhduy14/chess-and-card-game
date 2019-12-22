package vn.com.duykk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.com.duykk.service.CardService;
import vn.com.duykk.service.MoveService;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class TestLogicApplication implements CommandLineRunner {

  @Autowired
  MoveService moveService;

  @Autowired
  CardService cardService;

  public static void main(String[] args) {
    SpringApplication.run(TestLogicApplication.class, args);
  }

  public void run(String... args) throws Exception {
    this.menu();
    Scanner in = new Scanner(System.in);
    int option = in.nextInt();
    if (option == 1) {
      boolean validate = moveService.validateBishopMove();
      log.info("Validate : {}",validate);
      this.run();
    }
    if (option == 2) {
      moveService.findShortestPath();
      this.run();
    }

    //TODO: DuyKK Chua xong tim solution
    if (option == 3) {
      System.out.println(cardService.sortCard(cardService.dealCard()));
    }
  }

  private void menu() {
    System.out.println("MENU");
    System.out.println("1 : Validate Bishop Move");
    System.out.println("2 : Find shortest move");
    System.out.println("3 : Phom");
  }
}
