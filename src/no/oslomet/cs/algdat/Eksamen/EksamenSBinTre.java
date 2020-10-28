package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        // Kode hentet fra Programkode 5.2.3 a), fra kompendium av Ulf Utterud

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte
        // Ensete endring som gjøres etter kopiering er å sette 'q' som forelder i den nye noden
        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging


    }

    public boolean fjern(T verdi) {
        // Kopiert fra kompendium; Programkode 5.2.8 d)
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) {
                rot = b;
                // Dersom b ikke er null, sier vi at rots forelder er null
                if (b != null){
                    rot.forelder = null;
                }
            }
            else if (p == q.venstre) {
                // Dersom b ikke er null, sier vi at dens forelder er q
                if (b != null) {
                    b.forelder = q;
                }
                q.venstre = b;

            }
            else {
                // Dersom b ikke er null, sier vi at dens forelder er q
                if (b != null) {
                    b.forelder = q;
                }
                q.høyre = b;
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }
        antall--;   // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {

        if(antall > 0) {
            // Teller hvor mange fjernelser som gjøres
            int count = 0;
            // Jeg skjønner ikke helt at denne While-løkken fungerer, men etter å endelig å ha fått det til å fungere, tør jeg ikke stille spørsmål :)
            while (fjern(verdi)) {
                    count++;
            }
            // Returnerer telleren.
            return count;
        }
        else {
            return 0;
        }
    }

    public int antall(T verdi) {
        // Teller
        int count = 0;

        Node<T> current = rot;
        // Mens current ikke er null
        while (current != null){
            // Sammenligner verdier
            int compRes = comp.compare(verdi, current.verdi);

            // Hvis sammenligningen gir at verdi < current.verdi, går vi til venstre
            if (compRes < 0){
                current = current.venstre; // iterate downwards
            }
            // verdi > current.verdi, går til høyre
            if (compRes >= 0){
                // Hvis verdien er den samme, øker vi telleren
                if (current.verdi == verdi) {
                    count++;
                }
                // Itererer nedover
                current = current.høyre;
            }
        }
        // Returnerer teller
        return count;

    }

    public void nullstill() {
        // mens vi har noder i treet
        while(antall > 0){
            // går vi til første post-orden. Den vil alltid være på bunnen av en gren.
            Node<T> current = førstePostorden(rot);
            // Null'er alle attributter
            current.verdi = null;
            current.forelder = null;
            current.venstre = null;
            current.høyre = null;
            // Minimerer antallet
            antall--;
        }
        // null'er til slutt rotnoden.
        rot = null;

    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        // Kildekode hentet fra Kompendium: Programkode 5.1.7 h)
        while (true){
            if (p.venstre != null) p = p.venstre;
            else if(p.høyre != null) p = p.høyre;
            else return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        // Sjekker først om forelderen er null, da er vi på siste, og neste fins ikke.
        if (p.forelder != null) {
            // Hvis p er høyre barn, er forelderen neste.
            if (p.forelder.høyre == p) return p.forelder;
            // Hvis p er venstre barn
            else if(p.forelder.venstre == p){
                // Hvis venstrebarn er enebarn, er forelder neste
                if (p.forelder.høyre == null) return p.forelder;
                // Ellers er neste førstePostOrden til forelders høyrebarn
                else return førstePostorden(p.forelder.høyre);
            }
        }
        // Hvis alle over feiler, er forelder lik 'null', så vi har ingen neste.
        return null;
    }

    public void postorden(Oppgave<? super T> oppgave) {

        // Finner startNoden, vha. førstePostOrden
        Node<T> current = førstePostorden(rot);

        // Gjør 'oppgave' på førstePostorden
        oppgave.utførOppgave(current.verdi);

        // While løkke som utføres frem til den når rot-noden (node uten forelder)
        while (current.forelder != null) {
            current = nestePostorden(current);
            oppgave.utførOppgave(current.verdi);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        // Hvis vi befinner oss i rot, rekurserer vi med førstePostorden.
        if (p.forelder == null){
            postordenRecursive(førstePostorden(p), oppgave);
        }

        else {
            // Utfører oppgaven.
            oppgave.utførOppgave(p.verdi);
            // Dersom neste ikke er rot, rekurserer vi.
            if (nestePostorden(p) != rot) {
                postordenRecursive(nestePostorden(p), oppgave);
            }
            // Ellers befinner vi oss på nest-siste node. Har allerede har utført oppgaven for denne.
            else {
                // Mangler derfor bare å utføre oppgaven på siste node (rot-noden) før vi kan slutte
                oppgave.utførOppgave(rot.verdi);
            }
        }
    }

    public ArrayList<T> serialize() {

        // Denne oppgaven ble løst ved å se notater fra en av forelesningene, som omhandlet in-, pre- og postoreden.

        // Initialiserer ArrayListen
        ArrayList<T> outArray = new ArrayList<>();
        // Vi bruker en ArrayDeque, som lagrer noder i en dobbeltsidig kø
        ArrayDeque<Node<T>> dq = new ArrayDeque<>();
        // Legger til roten først i køen
        dq.addFirst(rot);
        // Looper gjennom deque'en helt til den er tom <=> alle elementene er gjennomgått
        while (!dq.isEmpty()){
            // Tar den første fra deque'en, og setter den som current.
            Node<T> current = dq.removeFirst();
            // Dersom venstre ikke er null, legger vi først den til deque'en
            if (current.venstre != null) {
                dq.addLast(current.venstre);
            }
            // Dersom høyre ikke er null, legger vi deretter den til deque'en
            if (current.høyre != null) {
                dq.addLast(current.høyre);
            }
            // Legger verdien til current i ArrayList'a
            outArray.add(current.verdi);
        }
        return outArray;
    }

    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {

        // Initialiserer et nytt tre
        EksamenSBinTre<K> out = new EksamenSBinTre<>(c);
        // Legger inn datapunktene fra data
        for (K value : data){
            out.leggInn(value);
        }
        return out;
    }


} //
