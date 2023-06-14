
# Battleship [![Docker Build & Push](https://github.com/softeng2223-inf-uniba/progetto2223-thompson/actions/workflows/docker_build&push.yml/badge.svg)](https://github.com/softeng2223-inf-uniba/progetto2223-thompson/actions/workflows/docker_build&push.yml)

La struttura della repository si presenta nel seguente modo:

```plaintext
|-- .github
|    |-- workflows
|    |      |-- docker_build&push.yml
|    |      |-- gradle_build.yml
|–– config
|    |–– checkstyle
|    |–– pmd
|–– docs
|    |–– Assegnazione progetto.md
|    |–– CODE_OF_CONDUCT.md
|    |–– Guida per lo studente.md
|    |–– ISPIRATORE.md
|    |–– img
|    |–– Report.md
|–– drawings
|–– gradle
|–– lib
|–– res
|–– src
|    |–– main
|    |–– test
|–– .gitignore
|–– build.gradle
|–– README.md
|–– gradlew
|–– gradle.bat
|–– settings.gradle
```

Nel seguito si dettagliano i ruoli dei diversi componenti:

- `.github/workflows/`: fornisce un workflow completo per automatizzare il processo di build del progetto utilizzando Gradle, seguito dalla creazione e dal push dell'immagine Docker nel registro GitHub Packages
  - `docker_build&push.yml`: automatizza il processo di build del progetto utilizzando Gradle, la creazione e il push dell'immagine Docker nel registro GitHub Packages;
  - `gradle_build.yml`: file di configurazione per un workflow di GitHub Actions;
- `config/`: ospita i file di configurazione. Le uniche configurazioni di base richieste sono quelle per il tool checkstyle e pmd;
- `docs/`: ospita la documentazione di progetto, incluse le immagini (nella sottocartella `img/`).
  La cartella raccoglie inoltre:
  - `Assegnazione progetto.md`: contenente la descrizione dettagliata del progetto assegnato;
  - `CODE_OF_CONDUCT.md`: contenente il codice di comportamento del gruppo;
  - `Guida per lo studente.md`: contenente la descrizione di tutti i passi di configurazione necessari per l'attivazione del flusso di lavoro a supporto dello sviluppo del progetto;
  - `ISPIRATORE.md`: contenente una breve biografia e principali contributi del personaggio a cui il gruppo si è ispirato per il nome: Ken Thompson;
  - `Report.md`: contenente la relazione tecnica del progetto.
- `gradle/`: ospita il `.jar` relativo al sistema di gestione delle dipendenze *Gradle*.
- `lib`: include eventuali librerie esterne utilizzate dal progetto.
- `res`: contiene risorse varie utilizzate dal sistema
- `src`: cartella principale del progetto, in cui è presente tutto il codice dell’applicazione. In `main/` sono presenti i file sorgente e in `test/` i test di unità previsti.
- `drawings/`: contiene tutti i diagrammi UML usati per descrivere il progetto.
- `.gitignore`: specifica tutti i file che devono essere esclusi dal sistema di controllo versione.
- `build.gradle`: esplicita le direttive e la configurazione di *Gradle*.
- `gradlew` e `gradlew.bat`: eseguibili di *Gradle*, rispettivamente dedicati a Unix e Windows.
- `settings.gradle`: file di configurazione di *Gradle*.

In alcune cartelle è possibile notare la presenza di un unico file nascosto `.keep`: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.
