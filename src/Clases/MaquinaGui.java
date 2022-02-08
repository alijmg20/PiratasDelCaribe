package Clases;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class MaquinaGui extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    private int id;
     private Map<String,Coordenada> coordenadas = new HashMap<String,Coordenada>();
     
    //maquina1
    private javax.swing.JLabel IslaNuevaEsperanzas;
    private javax.swing.JLabel PuertoReal;
    private javax.swing.JLabel PlayadelasPerlas;
    private javax.swing.JLabel CaladelosMartires;
    private javax.swing.JLabel CayodelBuenViento;
     private javax.swing.JLabel CayodeSotavento;
     
    //maquina2
    private javax.swing.JLabel IslaLaHolandesa;
    private javax.swing.JLabel PuertodeLaReina;
    private javax.swing.JLabel BahiadelaEsperanza;
    private javax.swing.JLabel CuevadelosMarineros;
    private javax.swing.JLabel CuevadelBucanero;
    private javax.swing.JLabel BahiadelBuenReposo;
    
    //maquina3
    private javax.swing.JLabel IslaTortuga;
    private javax.swing.JLabel CuevadelosHuesos;
    private javax.swing.JLabel BahiadelBuenRetiro;
    private javax.swing.JLabel BahiadelasFlores;
    private javax.swing.JLabel CementeriodeBarcos;
    private javax.swing.JLabel CayodeBarlovento;
    
    //maquina4
    private javax.swing.JLabel LaGranIsladelaEspañola;
    private javax.swing.JLabel CuevadelPirata;
    private javax.swing.JLabel CayodelosPelicanos;
    private javax.swing.JLabel BahiadelTesoroPerdido;
    private javax.swing.JLabel PuertoRico;
    private javax.swing.JLabel PuertodelasBallenas;
    
    public MaquinaGui(int id) {
        this.id = id;
        initComponents();
    }

   public Map<String,Coordenada> getCoordenadas (){
        return coordenadas;
    }
                            
    private void initComponents() {
        switch (id){
            
            case 1:
                this.setTitle("Maquina 1");
                PuertoReal = new javax.swing.JLabel();
                PlayadelasPerlas = new javax.swing.JLabel();
                CaladelosMartires = new javax.swing.JLabel();
                CayodelBuenViento = new javax.swing.JLabel();
                IslaNuevaEsperanzas = new javax.swing.JLabel();
                CayodeSotavento = new javax.swing.JLabel();
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMinimumSize(new java.awt.Dimension(800, 600));
                setPreferredSize(new java.awt.Dimension(800, 600));
                getContentPane().setLayout(null);
                PuertoReal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m1-puertoreal2.png")));
                getContentPane().add(PuertoReal);


                PuertoReal.setBounds(610, 310, 130, 90);

                coordenadas.put("Puerto Real", new Coordenada(610,310));

                PlayadelasPerlas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m1-perlas.png")));
                getContentPane().add(PlayadelasPerlas);
                PlayadelasPerlas.setBounds(240, 40, 140, 110);
                coordenadas.put("Playa de las Perlas", new Coordenada(240,40));

                CaladelosMartires.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m1-martires.png")));
                getContentPane().add(CaladelosMartires);
                CaladelosMartires.setBounds(450, 120, 80, 90);
                coordenadas.put("Cala de los Martires", new Coordenada(450,120));

                CayodelBuenViento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/CayodelBuenViento.png")));
                getContentPane().add(CayodelBuenViento);
                CayodelBuenViento.setBounds(60, 330, 270, 230);
                coordenadas.put("Cayo del Buen Viento", new Coordenada(60,330));
                
                CayodeSotavento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cayosotavento.png")));
                getContentPane().add(CayodeSotavento);
                CayodeSotavento.setBounds(20,40, 206, 211);
                coordenadas.put("Cayo de Sotavento", new Coordenada(20,40));
                
                IslaNuevaEsperanzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina1.png")));
                getContentPane().add(IslaNuevaEsperanzas);
                IslaNuevaEsperanzas.setBounds(0, 0, 800, 600);

                pack();
                
                break;
            case 2:
                
                this.setTitle("Maquina 2");
                PuertodeLaReina = new javax.swing.JLabel();
                BahiadelaEsperanza = new javax.swing.JLabel();
                CuevadelosMarineros = new javax.swing.JLabel();
                CuevadelBucanero = new javax.swing.JLabel();
                BahiadelBuenReposo = new javax.swing.JLabel();
                IslaLaHolandesa = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMinimumSize(new java.awt.Dimension(800, 600));
                setPreferredSize(new java.awt.Dimension(800, 600));
                getContentPane().setLayout(null);

                PuertodeLaReina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-puertoreina2.png"))); 
                getContentPane().add(PuertodeLaReina);
                PuertodeLaReina.setBounds(590, 380, 130, 100);
                coordenadas.put("Puerto de La Reina", new Coordenada(590,380));

                BahiadelaEsperanza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-esperanza.png"))); 
                getContentPane().add(BahiadelaEsperanza);
                BahiadelaEsperanza.setBounds(180, 230, 90, 110);
                coordenadas.put("Bahia de la Esperanza", new Coordenada(180,230));

                CuevadelosMarineros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-cuevamarinero.png"))); 
                getContentPane().add(CuevadelosMarineros);
                CuevadelosMarineros.setBounds(480, 70, 100, 80);
                coordenadas.put("Cueva de los Marineros", new Coordenada(480,70));

                CuevadelBucanero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-cuevabucanero.png")));
                getContentPane().add(CuevadelBucanero);
                CuevadelBucanero.setBounds(90, 450, 93, 90);
                coordenadas.put("Cueva del Bucanero", new Coordenada(90,450));

                BahiadelBuenReposo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-reposo.png")));
                getContentPane().add(BahiadelBuenReposo);
                BahiadelBuenReposo.setBounds(540, 230, 100, 80);
                coordenadas.put("Bahia del Buen Reposo", new Coordenada(540,230));

                IslaLaHolandesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina2.png")));
                getContentPane().add(IslaLaHolandesa);
                IslaLaHolandesa.setBounds(0, 0, 800, 600);

                pack();
            break;
                
            case 3:
                this.setTitle("Maquina 3");
                CuevadelosHuesos = new javax.swing.JLabel();
                BahiadelBuenRetiro = new javax.swing.JLabel();
                BahiadelasFlores = new javax.swing.JLabel();
                CementeriodeBarcos = new javax.swing.JLabel();
                CayodeBarlovento = new javax.swing.JLabel();
                IslaTortuga = new javax.swing.JLabel();
                
                
                BahiadelBuenRetiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m3-retiro.png")));
                getContentPane().add(BahiadelBuenRetiro);
                BahiadelBuenRetiro.setBounds(610, 400, 100, 80);
                coordenadas.put("Bahia del Buen Retiro", new Coordenada(610,400));

                BahiadelasFlores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m3-flores.png")));
                getContentPane().add(BahiadelasFlores);
                BahiadelasFlores.setBounds(220, 80, 90, 90);
                coordenadas.put("Bahia de las Flores",new Coordenada(220 , 80));

                CementeriodeBarcos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m3-cemeterio.png")));
                getContentPane().add(CementeriodeBarcos);
                CementeriodeBarcos.setBounds(140, 210, 100, 70);
                coordenadas.put("Cementerio de Barcos", new Coordenada(140,210));

                CayodeBarlovento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Barlovento.png")));
                getContentPane().add(CayodeBarlovento);
                CayodeBarlovento.setBounds(20, 290, 260, 180);
                coordenadas.put("Cayo de Barlovento", new Coordenada(20,290));

                CuevadelosHuesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m3-cuevahuesos.png")));
                getContentPane().add(CuevadelosHuesos);
                CuevadelosHuesos.setBounds(410, 430, 110, 80);
                coordenadas.put("Cueva de los Huesos",new Coordenada(410,430));

                IslaTortuga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina3.png")));
                getContentPane().add(IslaTortuga);
                IslaTortuga.setBounds(0, 0, 800, 600);

                pack();
                
                break;
                
            case 4:
                this.setTitle("Maquina 4");

                CuevadelPirata = new javax.swing.JLabel();
                CayodelosPelicanos = new javax.swing.JLabel();
                BahiadelTesoroPerdido = new javax.swing.JLabel();
                PuertoRico = new javax.swing.JLabel();
                PuertodelasBallenas = new javax.swing.JLabel();
                LaGranIsladelaEspañola = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMinimumSize(new java.awt.Dimension(800, 600));
                setPreferredSize(new java.awt.Dimension(800, 600));
                getContentPane().setLayout(null);



                CayodelosPelicanos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cayopelicano.png")));
                getContentPane().add(CayodelosPelicanos);
                CayodelosPelicanos.setBounds(350, 310, 470, 270);
                coordenadas.put("Cayo de los Pelicanos",new Coordenada (350, 310));

                BahiadelTesoroPerdido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m4-tesoroperdido.png")));
                getContentPane().add(BahiadelTesoroPerdido);
                BahiadelTesoroPerdido.setBounds(420, 210, 100, 80);
                coordenadas.put("Bahia del Tesoro Perdido",new Coordenada (420, 210));


                PuertoRico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m4-puertorico.png")));
                getContentPane().add(PuertoRico);
                PuertoRico.setBounds(460, 70, 93, 70);
                coordenadas.put("Puerto Rico",new Coordenada(460,70));

                PuertodelasBallenas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m4-puertoballena.png")));
                getContentPane().add(PuertodelasBallenas);
                PuertodelasBallenas.setBounds(90, 50, 90, 60);
                coordenadas.put("Puerto de las Ballenas", new Coordenada(90,50));

                CuevadelPirata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m4-cuevapiratag.png")));
                getContentPane().add(CuevadelPirata);
                CuevadelPirata.setBounds(170, 210, 100, 90);
                coordenadas.put("Cueva del Pirata", new Coordenada(170,210));

                LaGranIsladelaEspañola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina4.png")));
                getContentPane().add(LaGranIsladelaEspañola);
                LaGranIsladelaEspañola.setBounds(0, 0, 800, 600);

                pack();
                break;
        }
    }
}