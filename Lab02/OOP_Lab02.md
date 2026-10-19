

## Dane studenta
- Imię i nazwisko: Simone Polonis
- Numer albumu: 54413
- Grupa: 3
- Data: 13.10.2026
- Laboratorium nr: 2
- Temat: Interfejsy i ergonomia programowania obiektowego

## Cel ćwiczenia

* utrwalenie podstaw tworzenia klas i obiektów w Javie,
* wprowadzenie pojęcia interfejsu
* zrozumienie mechanizmu wymienności implementacji,

## Zadania

0. Pytanie kontrolne, co oznacza static?
- takie oznaczenie sprawia, że np. metoda należy do całej klasy, a nie tylko obiektu, w którym się znajduje.
1. Poniższy kod tworzy okienkową aplikacje licznika, jednak nie posiada jeszcze podłączonej obsługi wydarzeń na jej przyciskach.
   ```java
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    
    public class CounterApp {
    
        JFrame frame = new JFrame("Licznik");
        JButton button = new JButton("Kliknij!");
        JLabel label = new JLabel("0");
    
        public static void main(String[] args) {
            new CounterApp().run();
        }
    
        void run() {
            frame.setLayout(new FlowLayout());
            frame.add(button);
            frame.add(label);
    
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }
   ```

2.  Po dodaniu podstawowej obsługi wydarzeń, możemy sprawić że nasza aplikacja faktycznie zlicza i wyświetla kliknięcia w przycisk.

   ```java
    public class CounterApp implements ActionListener {}
    int count = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText(Integer.toString(count));
    }
    ```

   

3. Aplikacja po poniższych zmianach oraz dodaniu nowych klas licnzików, zyskuje możliwość przełączania się miedzy nimi za pomocą ComboBoxa. Przełączanie się miedzy nimi wyświetla też ich oddzielne wartości liczników.

   ```java
    package kirk;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    
    
    public class CounterApp implements ActionListener {
    
        int count = 0;
    
        @Override
        public void actionPerformed(ActionEvent e) {
            Counter counter = (Counter) counters.getSelectedItem();
            counter.increase();
            label.setText(Integer.toString(counter.currentValue()));
            frame.pack();
        }
        
        JFrame frame = new JFrame("Licznik");
        JButton button = new JButton("Kliknij!");
        JLabel label = new JLabel("0");
    
        JComboBox<Counter> counters = new JComboBox<>(new Counter[]{
            new SimpleCounter(), 
            new DoubleCounter(),
        });
        
        public static void main(String[] args) {
            new CounterApp().run();
        }
    
        void run() {
            frame.setLayout(new FlowLayout());
            frame.add(button);
            frame.add(label);
            frame.add(counters);	
            
            button.addActionListener(this);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }

   ```

4. Poprzez obsługę funkcji warunkowej, stworzono kolejną klasę OverflowCounter zliczającą kliknięcia do 10, a następnie powracająco do 0 przy następnym kliknięciu.


5. W celu wyświetlenia poprawnych nazw w polu wyboru ComboBoxa, należy nadpisać automatycznie generującą się metodę każdego obiektu ".toString()", aby zamiast lokalizacji bitowej obiektu/klasy po znaku "@", wyświetlała naszą własną nazwę
   

6. W celu dodania dodatkowego przycisku obsługującego resetowanie naszych liczników musimy dodać obsługę kilku wydarzeń do naszej aplikacji, spośród licznych sposobów wybrano dwa:


* Sposób I - dodanie funkcji warunkowych w polu obsługi wszystkich wydarzeń, aby aplikacja rozpoznawała oddzielne akcje na różnych przyciskach
  ```java
   public void actionPerformed(ActionEvent e) {
           if(e.getSource()==button) {
           Counter counter = (Counter) counters.getSelectedItem();
           counter.increase();
           label.setText(Integer.toString(counter.currentValue()));
           frame.pack();
       }
       else if(e.getSource()==buttonR) {
           Counter counter = (Counter) counters.getSelectedItem();
           counter.reset();
           label.setText(Integer.toString(counter.currentValue()));
           frame.pack();
       }
   }
   ```
* Sposób II - zakleszczenie obsługi wydarzeń w osobnych przyciskach
  ```java
      button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
                Counter counter = (Counter) counters.getSelectedItem();
              counter.increase();
              label.setText(Integer.toString(counter.currentValue()));
              frame.pack();
          }
      });
        
      buttonR.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              Counter counter = (Counter) counters.getSelectedItem();
              counter.reset();
              label.setText(Integer.toString(counter.currentValue()));
              frame.pack();
          }
      });
  ```

Odpowiadając na pytanie z zadania, aby obsłużyć 10 różnych wydarzeń dla przycisków, wybrałbym sposób I. Dla większej ilości obiektów bardziej wydajne jest użycie funkcji warunkowych, aniżeli zakleszczać nowe wydarzenie do każdej struktury przycisku.
