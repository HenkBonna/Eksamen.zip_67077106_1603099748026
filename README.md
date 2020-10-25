# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Vi har brukt git til å dokumentere arbeidet vårt. Jeg har 16 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

* Oppgave 1: Løste ved å kopiere den gitte kildekoden fra kompendiet; Programkode 5.2.3 a). Måtte bare sørge for at noden 
også fikk en foreldre peker.
* Oppgave 2: ...
* Oppgave 3:
* Oppgave 4: Denne tok en del tid å debugge. Måtte finne en løsning for å avslutte rekursjonen, da den også må fungere 
for rot-noden. Å sjekke om nåværende node er rot, er dermed ikke et punkt man kan ende på. Gjorde derfor rekursjon bare 
frem til man befant seg på nest siste node. 
* Oppgave 5: Løsningen til Serialize var basert på notater fra en forelesning jeg hadde tidligere, men med noen mindre 
endringer for å få det til å fungere. Jeg snubla litt langs veien ved å bruke 'removeLast' da jeg egentlig burde ha 
brukt 'removeFirst', men dette klarte jeg løse vha. System.out.print()-debugging, og IntelliJ's verktøy.
* Oppgave 6:
