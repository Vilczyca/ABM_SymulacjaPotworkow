# Symulacja Potworków

Julia Niśkiewicz (lider), Maksymilian Łukaszewski

## Temat

Tematem niniejszej pracy jest zbadanie interakcji pomiędzy różnymi gatunkami potworków w turowej symulacji. Jest ona
przykładem wieloagentowego modelu symulacyjnego (Agent Based Model, w skrócie ABM), inspirowana modelem Predator-Prey.
Napisana została w języku Java. Głównym celem jest zbadanie, jakie umiejętności najbardziej zwiększają szanse na
przeżycie gatunku - początkowa przewaga siły, możliwość ewolucji czy zdolność do rozmnażania.

## Opis zagadnienia symulacji w języku naturalnym

### Plansza oraz parametry początkowe

“Środowisko”, w którym żyją potworki to plansza składająca się z sześciokątnych pól. Na każdym z nich może się znajdować
tylko 1 osobnik lub jedzenie. Istnieje 5 rodzajów pól - łąka, góry, pustynia, woda, zatopione. Użytkownik może sam
ustalić, jak będzie wyglądał krajobraz - zadaje procentowy udział poszczególnych typów pól, a także populację potworków,
czyli ile procent pól będzie zasiedlonych przez potworki. Potwory oraz jedzenie poszczególnych rodzajów pojawią się na
odpowiadających im polach.

### Interakcje pomiędzy potworkami (Agentami)

Każdy potwór ma podstawową statystykę - punkty doświadczenia (EXP), które wpływają na ich możliwości. W każdej turze
potworki mogą się poruszać, czyli przemieszczać się do najbliższego pola oraz walczyć między sobą, jeśli takowe pole
będzie zajęte. Walkę wygrywa osobnik o większym EXP, w przypadku remisu przewagę ma atakujący. Zwycięzca dostaje punkty
EXP przeciwnika, przegrany zostaje usunięty z symulacji. Jeśli potwór przemieści się na pole na którym znajduje się
jedzenie zjada je, zyskując niewiele punktów doświadczenia.

### Rodzaje potworów (Agentów)

- Potwory podstawowe (Monster) - mają tylko podstawowe umiejętności
- Potwory specjalistyczne
    - Górskie (MountainMonster) - zdobywają 2 razy więcej doświadczenia niż inne potwory, przez co na początku są
      silniejsze.
    - Pustynne (DesertMonster) - jako jedyne są w stanie się rozmnażać po przeskoczeniu konkretnego progu doświadczenia.
    - Wodne (LakeMonster) - są podobne do podstawowych, ale po osiągnięciu odpowiedniego poziomu EXP potrafią ewoluować
      zyskując dodatkową umiejętność zatapiania pól, czyli przekształcania obecnie zajmowanego pola w pole “zatopione”,
      na które inne potwory nie mogą wchodzić.

### Panele sterujące oraz zbierane dane

Po zadaniu parametrów początkowych użytkownik nie ma wpływu na to, co się dzieje na planszy, ale może dostosować
obserwacje do swoich potrzeb. Po kliknięciu wybranego pola z planszy, w lewym panelu (Szczegóły) są wyświetlane
informacje dotyczące między innymi potworów, jedzenia oraz samego terenu. Prawy panel (Stan symulacji) pozwala na
sterowanie szybkością wyświetlania za pomocą suwaka(pokazuje co ile milisekund zmienia się “klatka” symulacji”),
dostępne są również guziki do pauzowania oraz wychodzenia z symulacji. W momencie jej opuszczenia tworzy lub nadpisuje
się plik “simulation_result.txt”, zawierający informacje o parametrach początkowych oraz końcowych (w momencie wyjścia).