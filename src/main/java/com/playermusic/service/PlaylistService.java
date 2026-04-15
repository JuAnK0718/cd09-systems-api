package com.playermusic.service;

import com.playermusic.model.Song;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    private class Nodo {
        Song dato;
        Nodo siguiente;
        Nodo anterior;
        Nodo(Song dato) { this.dato = dato; }
    }

    private Nodo cabeza, cola, actual;

    public void addSongAtEnd(Song song) {
        Nodo nuevo = new Nodo(song);
        if (cabeza == null) { cabeza = cola = actual = nuevo; }
        else { cola.siguiente = nuevo; nuevo.anterior = cola; cola = nuevo; }
    }

    // ELIMINAR CANCIÓN Y RECONECTAR NODOS
    public void deleteSong(String id) {
        if (cabeza == null) return;
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.dato.getId().equals(id)) {
                if (temp == cabeza) {
                    cabeza = temp.siguiente;
                    if (cabeza != null) cabeza.anterior = null;
                } else if (temp == cola) {
                    cola = temp.anterior;
                    if (cola != null) cola.siguiente = null;
                } else {
                    temp.anterior.siguiente = temp.siguiente;
                    temp.siguiente.anterior = temp.anterior;
                }
                if (actual == temp) actual = cabeza;
                break;
            }
            temp = temp.siguiente;
        }
    }

    public Song getNextSong() {
        if (actual != null && actual.siguiente != null) {
            actual = actual.siguiente;
            return actual.dato;
        }
        return (actual != null) ? actual.dato : null;
    }

    public Song getPreviousSong() {
        if (actual != null && actual.anterior != null) {
            actual = actual.anterior;
            return actual.dato;
        }
        return (actual != null) ? actual.dato : null;
    }

    public void setActualById(String id) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.dato.getId().equals(id)) { actual = temp; break; }
            temp = temp.siguiente;
        }
    }

    public List<Song> getAllSongs() {
        List<Song> lista = new ArrayList<>();
        Nodo temp = cabeza;
        while (temp != null) { lista.add(temp.dato); temp = temp.siguiente; }
        return lista;
    }
}