Balaci Alexandra - Daniela, 323CD

Pentru citirea din fisier si implementarea simularii am creat clasele entitate Consumer, Distributor si Producer.

Am creat cate o interfata atat pentru Consumers, cat si pentru Distributors si Producers, in ideea
prezentari generale a metodelor entitatilor, intrucat extinderea acestora nu a fost necesara (toate entitatile au fost
de un singur tip).

Clasele utilitare GetAvailableContracts si RegisterUpdates au fost create pentru a nu incarca clasa Main cu logica
necesara efectuarii acestor operatii.

Intrucat a fost necesara crearea de entitati noi de tip Consumer in momentul citirii update-urilor lunare, acestia au
fost creati folosind clasa ConsumersFactory si adaugati listei deja existente de consumatori, rezultata din citirea din
fisier.

Clasele Factory (ConsumersFactory si EnergyChoiceStrategyFactory) au fost instantiate folosind pattern-ul Singleton,
intrucat este suficienta existenta unei singure instante a acestora in JVM in orice moment.

Design Pattern-ul Strategy a fost implementat in crearea strategiilor necesare de alegere a Producatorilor, apoi
s-a folosit Factory pentru crearea strategei necesare fiecarui distribuitor, in scopul decuplarii procesului de creatie
de entitate in sine.

Design Pattern-ul Observer a fost implementat pentru anuntarea Producatorilor de modificarile survenite asupra
sistemului de simulare. Astfel, clasa Observable SystemSimulation este updatata cu producatorii care isi modifica
energia lunara, anuntand apoi fiecare producator de schimbarile aparute. Acestia verifica daca au fost afectati,
si, in caz afirmativ, isi modifica energia dupa caz si isi modifica statutul in hasChanged. Variabila hasChanged este
folosita ulterior de distribuitori pentru a verifica daca unul (sau mai multi) din producatorii de la care ia energie
si-a modificat cantitatea distribuita, si, daca este cazul aplica din nou strategia de selectie a producatorilor.

Logica programului este simpla, clasele entitate se ocupa de metodele necesare prelucrarii obiectelor, metode care sunt
apoi apelate in clasa Main ( se parcurg listele obtinute din parsarea input ului si se apeleaza modificarile necesare
functionarii programului - indexarea update-urilor, primirea salariului, alegerea si platirea contractului etc), apoi se
apeleaza clasele utilitare pentru scrierea in fisier.
Scopul fiecarei clase si a metodelor este documentat in javadocs-ul corespunzator.
