package com.studentmanagement.repository;

import com.studentmanagement.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassRoom,Long> {
}
