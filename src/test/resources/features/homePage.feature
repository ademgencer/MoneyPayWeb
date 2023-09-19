Feature: Ana sayfadaki logo ve header menu islevselliği test edilir.

  Scenario: Kullanici moneypay logoya click yapar ve header menudeki ana sekmelere hover yapar.

    Given Kullanici ana sayfaya girer
    When monayPay logosuna click yapar
    Then Ana sayfaya navigate olmalidir
    When Kullanici header menu ve altındaki submenu lere hover over yapar
    Then Hover over yapılabilmelidir