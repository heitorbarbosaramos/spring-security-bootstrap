package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
