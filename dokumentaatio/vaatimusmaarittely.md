# Vaatimusmäärittely

## Yleistä sovelluksesta

Kyseessä on vuoropohjainen taktiikka- ja puzzlepeli, jossa tarkoituksena on päihittää vastustajat ja selvitä ulos kapakasta mahdollisimman ehjin nahoin.

## Käyttäjät

Vain yksi eli pelaaja.

## Käyttöliittymästä

Sovellus koostuu yhdestä näkymästä, joka sisältää pelialueen, mahdollista pelaajalle annettavaa tietoa (esimerkiksi monesko vuoro on meneillään) sekä tekstialueen, johon peliin liittyvät viestit tulevat näkyviin. Graafisesti käyttöliittymä on tarkoituksellisesti yksinkertainen (ASCII grafiikka).



|---------------/ ------|
|           |           | 
|   @       |           |
|           |   V V     |  <- pelialue
|   |  V                |
|   |                   |
|-----------------------|
|vuoro 5                |  <- tietolaatikko
|-----------------------| 
| Osuit Daveen!         |
| Dave joi oluen!       |  <- tekstialue
| ...ja muita viestejä  |
|-----------------------|




## Perusversion toiminnallisuus

* Peli on pelattavissa
* Pelissä on ainakin seuraavat hahmot
  * Pelaaja
  * Tietokonevastustaja
* Tietokonevastustajilla on tekoäly, jonka puitteissa ne toimivat
* Hahmojen on mahdollista suorittaa erinäisiä toimintoja, kuten hyökkääminen ja liikkuminen.
* Pelaaminen tapahtuu näppäimistöllä
* Vastustajahahmoilla on nimet, jotka haetaan tiedostosta tai tietokannasta. Nimilista on pelaajien vapaasti muokattavissa.
* Pelin voi voittaa nujertamalla vastustajat
  * Vastavuoroisesti pelin häviää, jos vastustajat nujertavat pelaajan
* Pelissä on useampia ratoja (kenttiä), jotka poikkeavat toisistaan
* Peli sisältää esineitä, joiden kanssa voi vuorovaikuttaa
  * Olutpullo
    * Voidaan juoda ja/tai käyttää aseena
  * Puukko
    * Voidaan käyttää aseena 

## Jatkokehitysideoita

* Hiirellä pelaaminen, joko suoraan pelialuetta klikkailemalla tai virtuaalinäppiksen välityksellä.
* Pelin pisteytys ja high-score ranking
* Lisää erilaisia hahmoja
  * Kapakan omistaja
  * Poliisi
  * Erilaisia vastustajatyyppejä
* Lisää erilaisia esineitä
* PC Speaker tyyliset äänitehosteet
* Kenttägeneraattori
* Mahdollisuus tehdä itse omia kenttiä
