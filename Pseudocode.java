Klasse CreatePairsClass:

  Funktion createPairs(participants):
    Sortiere Teilnehmer nach FoodPreference
    Initialisiere leere Paarliste
    Durchlaufe Teilnehmer:
      Durchlaufe nachfolgende Teilnehmer:
        Wenn Küchenstatus zwischen beiden Teilnehmern erfüllt:
          Erstelle Paar und füge es der Paarliste hinzu
    Gib Paarliste zurück

  Funktion createPairs2(participants):
   Ähnlich wie createPairs, aber paare Teilnehmer basierend auf minimalen Altersunterschied

  Funktion createPairs3(participants):
   Ähnlich wie createPairs, minimert die Anzahl der Nachrückende.


Klasse CreateGroupsClass:

  Variablen: 
    - Paare, Gruppen, Küchen (Listen)
    - Teilnehmer (Liste von Participants)

  Konstruktor(CreateGroupsClass):
    Initialisiere Listen von Paaren, Gruppen, Küchen und Teilnehmern.

  Konstruktor(CreateGroupsClass) ohne Parameter:
    Initialisiere Listen von Paaren, Gruppen und Küchen.

     Funktion chooseCreatePairsMethod(userChoice, participants)
        Wähle die passende createPairs Methode basierend auf der Nutzerwahl (Nach Kriterien)

        Funktion sortPairsByFoodPreference()
        Sortiere Paare basierend auf ihrer FoodPreference in separaten Listen also 4 Listen haben wir dann
        MeatList, NoneList, VeganList, VeggieList

        Funktion createGroupsFromSortedPairs(sortedPairs)
        Erstelle Gruppen aus sortierten Paaren

        Funktion createGroupsWithNinePairs(pairList)
        Erstelle Gruppen mit genau 9 Paaren auf drei Gänge => (die wird  in createGroupsFromSortedPairs immer aufgerufen)

  Funktion getUserChoice:
    - Rufe die Methode getUserChoice in UIUtils auf und gib die Auswahl des Benutzers zurück.

Erklärung :
Die Klasse "CreatePairsClass" enthält mehrere Funktionen zur Erstellung von Paaren basierend auf verschiedenen Kriterien. 
Die Funktion "createPairs" sortiert die Teilnehmer nach ihren FoodPreferences und erstellt dann Paare, indem sie über die Teilnehmerliste iteriert und für jeden Teilnehmer nachfolgende Teilnehmer überprüft. 
Wenn der Küchenstatus zwischen den beiden Teilnehmern erfüllt ist, wird ein Paar erstellt und der Paarliste hinzugefügt.
Die Funktion "createPairs2" erstellt Paare basierend auf dem minimalen Altersunterschied der Teilnehmer, während "createPairs3" die Anzahl der Nachrückenden minimiert.
Die Klasse "CreateGroupsClass" enthält Variablen für Paare, Gruppen, Küchen und Teilnehmer.
Es gibt zwei Konstruktoren: einer mit Parametern, der die Listen von Paaren, Gruppen, Küchen und Teilnehmern initialisiert, und einer ohne Parameter, der nur die Listen von Paaren, Gruppen und Küchen initialisiert.
Die Funktion "chooseCreatePairsMethod" in der Klasse "CreateGroupsClass" wählt die passende "createPairs"-Methode basierend auf der Auswahl des Benutzers aus.
Es gibt auch eine Funktion "sortPairsByFoodPreference", die die Paare basierend auf ihrer FoodPreference sortiert, und eine Funktion "createGroupsFromSortedPairs", die Gruppen aus den sortierten Paaren erstellt.
Eine weitere Funktion "createGroupsWithNinePairs" erstellt Gruppen mit genau neun Paaren, die auf drei Gänge aufgeteilt werden.
Die Funktion "getUserChoice" ruft die Methode "getUserChoice" in der Klasse "UIUtils" auf und gibt die Auswahl des Benutzers zurück.
Die beschriebenen Klassen und Funktionen bieten eine Struktur und Methoden zur Erstellung von Paaren und Gruppen basierend auf verschiedenen Kriterien wie FoodPreference, Altersunterschied und Minimierung der Nachrückenden.
Sie bieten Flexibilität und Anpassungsfähigkeit bei der Zusammenstellung von Gruppen für verschiedene Zwecke.
	