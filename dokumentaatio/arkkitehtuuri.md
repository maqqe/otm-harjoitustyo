# Arkkitehtuurikuvaus

## Rakenne

Pelin käyttöliittymä on pakkauksessa barfightsimulator olevassa tiedostossa MainApp.java. MainApp java tekee instanssit ui ja dao pakkauksissa olevista MissionLoader.java ja LocalizableObjectDao luokista.
MissionLoader luokka hyödyntää DAO luokkaa pelin hahmojen ja esineiden koordinaattien hakemiseen.
Varsinainen pelin pyörittäminen tapahtuu MissionLoader luokan alla. MainApp hakee MissionLoader luokalta
pelitilanteen joka vuoro ja piirtää hahmot saatujen koordinaattien perusteella.

(Luokat jäivät epäloogisiin pakkauksiin pelin kasvaessa. Myöhemmät yritykset siirtää luokat parempiin pakkauksiin johtivat virheisiin ohjelman suorituksessa eikä niitä siten saatu siirrettyä järkevämpiin pakkauksiin.)

## Luokkakaavio

Alla LocalizableObjectin perivien luokkien hierarkia:

![Luokkakaavio](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/pics/luokkakaavio.png)

## Päätoiminnallisuudet

Pelaajan hyökätessä vihollisen kimppuun on toimintalogiikka sekvenssikaaviona esitettynä seuraava

![attackSequenceDiagram](https://github.com/maqqe/otm-harjoitustyo/blob/master/dokumentaatio/pics/attackSequenceDiagram.png)

Kun pelaaja antaa järkevän komennon graafisessa käyttöliittymässä, ohjataan komento MissionLoaderin metodille playTurn(String command), joka vastaa käytännön toteutuksesta.
Metodi siirtää ensin pelaajaa komennon mukaisesti, tai käyttää esineen. Mikäli pelaajaa siirretään, tarkastetaan, onko kohderuudussa vihollinen. Jos, sen kimppuun hyökätään ja pelaaja pysyy paikallaan.
Mikäli ruutu on tyhjä, liikutetaan pelaajaa. Epäjärkevät komennot johtavat tässä vaiheessa metodista poispaluuseen.
Tämän jälkeen peli tarkistaa, onko vihollisia hengissä. Mikäli ei, siirrytään seuraavaan tehtävään tai mikäli tehtävä oli viimeinen, lopetetaan peli. Mikäli vihollisia on jäljellä, jokaiselle kutsutaan ensin attack() metodia, jonka jälkeen ne jahtaavat pelaajaa mikäli eivät jo ole viereisessä ruudussa tai estyneitä. Tämän jälkeen tarkistetaan, onko pelaaja vielä hengissä (hit points >= 0). Mikäli ei, peli päättyy. Mikäli kyllä, poistutaan metodista odottamaan mahdollista uutta komentoa.

## Tiedostorakenteesta

config.properties määrittelee tiedostot, joista ratojen esineet ja viholliset luodaan. Tiedot on tallennettu seuraaviin muotoihin.

itemlocations.txt

> noItems
> 6,7,BEER;3,4,KNIFE
> 3,8,KNIFE

Eri radat on eritelty omille riveilleen. noItems tarkoittaa, että ettei radassa ole esineitä. Muuten esineet on eroteltu toisistaan puolipisteillä ja esineiden parametrit pilkuilla (x-koordinaatti, y-koordinaatti, esinetyyppi).

enemylocations.txt

> 1,1;
> 2,2;8,8
> 3,3;9,9;7,7

Eri viholliset on eroteltu puolipisteillä ja vihollisten parametrit pilkulla (x-koordinaatti, y-koordinaatti).

names.txt sisältää listan nimiä eroteltuna omille riveilleen. Yli 20 merkin nimet cropataan 20 merkin mittaisiksi, kun ne assignoidaan vihollisille.
