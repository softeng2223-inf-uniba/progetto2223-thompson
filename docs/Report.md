# Report

# Introduzione

**Battleship** è un'applicazione che permette di giocare a battaglia navale, con interfaccia a linea di comando (CLI). Per lo sviluppo delle implementazioni descritte in seguito si è formato un team denominato "Thompson" composto da cinque ragazzi del corso B del secondo anno di "informatica" dell'Università degli studi di Bari Aldo Moro durante l'A.A. 2022/23.

I componenti del gruppo sono:
- Lorenzo Matera
- Nicola Mastromarino
- Leo Mastronardi
- Emanuele Russo
- Roberto Rotunno

## 3 Requisiti specifici

### 3.1 Requisiti funzionali

<ul>
<li> <p><strong>Come giocatore voglio mostrare l'help con elenco comandi</strong><br></p></li>

<li> <p><strong>Come giocatore voglio chiudere il gioco</strong><br></p></li>

<li> <p><strong>Come giocatore voglio impostare il livello di gioco per variare il numero massimo di tentativi sbagliati</strong><br></p></li>

<li> <p><strong>Come giocatore voglio mostrare il livello di gioco e il numero di massimo di tentativi falliti</strong><br></p></li>

<li> <p><strong>Come giocatore voglio mostrare i tipi di nave e i numeri</strong><br></p></li>

<li> <p><strong>Come giocatore voglio iniziare una nuova partita</strong><br></p></li>

<li> <p><strong>Come giocatore voglio svelare la griglia con le navi posizionate</strong><br></p></li>

</ul>

### 3.2 Requisiti non funzionali
<ul>
<li> <p><strong>Il container docker dell'app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16</strong><br>
Elenco di terminali supportati<br>
Linux:<br>
- terminal<br>
Windows<br>
- Powershell<br>
- Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ...)<br>

**Comando per l’esecuzione del container**<br>
Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, Il comando Docker da usare per eseguire il container contenente l’applicazione è:<br>

`docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-thompson:latest`

</p></li>
</ul>