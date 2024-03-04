package org.presidenteapp.entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Jugador {
    private String nombre;
    private int puntos;
    private List<Carta> cartas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void addCarta(Carta e) {
        this.cartas.add(e);
    }

    public void addCartas(List<Carta> e){
        this.cartas.addAll(e);
    }

//    public void jugarCartas(List<Carta> cartasATirar, boolean escalera, boolean primero, Integer cantCartas){
//
//    }

//    public List<Carta> jugarCartas(Mazo mazo, boolean escalera, boolean primero, Integer cantCartas) throws IOException {
//        //MENSAJES ERROR
//        String e1 = "Las cartas ingresadas no son mayores a las que ya habia en el mazo. Ingrese nuevamente ";
//        String e2 = "Los numeros de las cartas ingresadas no son iguales. Ingrese nuevamente";
//        String e3 = "Sos el primero en jugar. Tenes que tirar una carta obligatoriamente";
//        String e4 = "Ingrese la cartas a jugar o marque 0 si quiere pasar el turno: ";
//        String e5 = "Carta incorrecta, intente nuevamente";
//        String e6 = "Cantidad de cartas incorrectas, se deben tirar " + cantCartas + " en esta ronda. Ingrese nuevamente";
//        String e7 = "La carta ingresada no es consecutiva a la ultima carta, porfavor seleccione otra o marque 0 si quiere pasar el turno: ";
//        String e8 = "RONDA ESCALERA: TIRAR " + cantCartas + " O INGRESAR 0 SI QUERES PASAR EL TURNO";
//
//
//        ArrayList<Integer> array = new ArrayList<>();
//        System.out.println("Jugador " + this.getNombre() + ": " + this.getCartas());
//        String s = "";
//
//        //SI SOY PRIMERO
//        if(primero && cantCartas==null){
//            s = "Arrancas la ronda, ingrese la cartas a jugar delimitadas con /: ";
//            array = this.pedirCartas(s);
//            while(array.get(0) == -1 || array.get(0) > this.cartas.size()){
//                if(array.get(0) == -1){
//                    array = this.pedirCartas(e3);
//                } else{
//                    array = this.pedirCartas(e5);
//                }
//            }
//            return this.devolverCartas(array);
//
//        } else if (escalera) {
//            array = this.pedirCartas(e8);
//            if(array.get(0) == -1 ){ //SI QUIERE PASAR TURNO
//                return this.devolverCartas(array);
//            }
//            while(!this.sonConsecutivos(this.cartas.get(array.get(0)).getNumero(), mazo.getUltima().getNumero()) || this.cartas.get(array.get(0)).getOrden() < mazo.getUltima().getOrden()) {
//                array = this.pedirCartas(e7);
//                if(array.get(0) == -1 ){ //SI QUIERE PASAR TURNO
//                    return this.devolverCartas(array);
//                }
//            }
//            return this.devolverCartas(array);
//        } else if (!mazo.getMazo().isEmpty()){
//            array = this.pedirCartas(e4);
//            if(array.get(0) == -1 ){ //SI QUIERE PASAR TURNO
//                return this.devolverCartas(array);
//            }
//            while(this.cartas.get(array.get(0)).getOrden() < mazo.getUltima().getOrden() || !this.areAllEqual(array) || array.size() != cantCartas){
//                if(!this.areAllEqual(array)){ //SI LAS DOS CARTAS NO SON IGUALES!
//                    array = this.pedirCartas(e2);
//                } else if(array.size() != cantCartas){ //SI NO SE TIRAN CANTCARTAS IGUALES A LA RONDA
//                    array = this.pedirCartas(e6);
//                } else if(this.cartas.get(array.get(0)).getOrden() < mazo.getUltima().getOrden()) { // SI NUMERO A TIRAR ES MAS CHICO QUE EL ULTIMO DEL MAZO
//                    array = this.pedirCartas(e1);
//                }
//                if(array.get(0) == -1 ){ //SI QUIERE PASAR TURNO
//                    return this.devolverCartas(array);
//                }
//            }
//            //TODO cuando cantCartas >=2, que la sumatoria de los ordenes de las cartas(que sean iguales) a tirar sea mayor a las ultimas cantCartas en el mazo.
//
//
//            return this.devolverCartas(array);
//        }
//        List<Carta> c = new ArrayList<>();
//        return c;
//    }
//    public ArrayList<Integer> pedirCartas(String mensaje){
//        ArrayList<Integer> array = new ArrayList<>();
//        try{
//            System.out.println(mensaje);
//
//            Scanner input = new Scanner (System.in);
//            String entrada = input.nextLine();
//
//            String[] arr = entrada.split("/");
//
//            for(String ar : arr){
//                array.add(Integer.parseInt(ar)-1);
//            }
//            return array;
//        } catch (Error e){
//            System.out.println(e);
//        }
//        return array;
//    }

    private boolean areAllEqual(ArrayList<Integer> lista) {
        int first = this.cartas.get(lista.get(0)).getNumero();
        for (int i = 1; i < lista.size(); i++) {
            if (this.cartas.get(lista.get(i)).getNumero() != first) {
                return false; // Si se encuentra un elemento diferente, los números no son todos iguales
            }
        }
        return true;
    }

    public boolean sonConsecutivos(int num1, int num2){
        int i = Math.abs(num2 - num1);
        if(i == 1){
            return true;
        }
        return false;
    }


    public List<Carta> devolverCartas(ArrayList<Integer> array){
        List<Carta> c = new ArrayList<>();

        for(Integer i : array){
            if(i == -1) {
                return c;
            }
            c.add(this.cartas.get(i));
            //this.cartas.remove(i);
        }
        return c;
    }

    public List<Carta> getPeoresCartas(int cant){
        Carta peorCarta = null;
        ArrayList peoresCartas = new ArrayList();
        for(int i = 1; i<=cant; i++){
            for(Carta c : this.cartas){
                if(peorCarta == null){
                    peorCarta = c;
                } else if (c.getOrden() < peorCarta.getOrden()) {
                    peorCarta = c;
                }
            }
            peoresCartas.add(peorCarta);
            this.cartas.remove(peorCarta);
            peorCarta = null;
        }
        if(cant==1){
            System.out.println("El vicepresidente ha dado al anteultimo: " + peoresCartas);
        }
        System.out.println("El presidente ha dado al ultimo: " + peoresCartas);
        return peoresCartas;
    }

    public List<Carta> getMejoresCartas(int cant){
        Carta mejorCarta = null;
        ArrayList mejoresCartas = new ArrayList();
        for(int i = 1; i<=cant; i++){
            for(Carta c : this.cartas){
                if(mejorCarta == null){
                    mejorCarta = c;
                } else if (c.getOrden() > mejorCarta.getOrden()) {
                    mejorCarta = c;
                }
            }
            mejoresCartas.add(mejorCarta);
            this.cartas.remove(mejorCarta);
            mejorCarta = null;
        }
        if(cant==1){
            System.out.println("El anteultimo ha dado al vicepresidente: " + mejoresCartas);
        }
        System.out.println("El ultimo ha dado al presidente: " + mejoresCartas);
        return mejoresCartas;
    }


    @Override
    public boolean equals(Object o) {
        Jugador jugador = (Jugador) o;
        if(this.getNombre().equals(((Jugador) o).getNombre())){
            return true;
        }
        return false;
    }

}
