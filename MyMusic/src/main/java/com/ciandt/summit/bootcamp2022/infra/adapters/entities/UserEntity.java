package com.ciandt.summit.bootcamp2022.infra.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private String id;
    @Column(name="Nome")
    private String name;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "PlaylistId", referencedColumnName = "Id")
    private PlaylistEntity playlist;

    public UserEntity() {
    }

    public UserEntity(String id, String name, PlaylistEntity playlist) {
        this.id = id;
        this.name = name;
        this.playlist = playlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaylistEntity getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistEntity playlist) {
        this.playlist = playlist;
    }

    public User toUser() {
        return new User(this.id, this.name, this.playlist.toPlaylist());
    }
}
