```plantUML
@startuml

class App

package controller {
  class InstitutoController {
    +empezar()
  }
}

package view {
  class ViewController
  class ViewAlumno
  class ViewProfesor
}

package model {
  class Alumno
  class Profesor
  class Instituto
  class Modo
  class InstitutoFactory
}

package dao {
  interface DAO
  interface AlumnoDAO
  interface ProfesorDAO

  DAO <|-- AlumnoDAO
  DAO <|-- ProfesorDAO

  package oracle {
    class ConexionOracle
    class AlumnoOracleXeDAOImp
    class ProfesorOracleXeDAOImp

    AlumnoDAO <|.. AlumnoOracleXeDAOImp
    ProfesorDAO <|.. ProfesorOracleXeDAOImp

    AlumnoOracleXeDAOImp --> ConexionOracle
    ProfesorOracleXeDAOImp --> ConexionOracle
  }

  package sqlite {
    class ConexionSQLite
    class AlumnoSQLiteDAOImp
    class ProfesorSQLiteDAOImp

    AlumnoDAO <|.. AlumnoSQLiteDAOImp
    ProfesorDAO <|.. ProfesorSQLiteDAOImp

    AlumnoSQLiteDAOImp --> ConexionSQLite
    ProfesorSQLiteDAOImp --> ConexionSQLite
  }

  package mock {
    class AlumnoMockDAOImp
    class ProfesorMockDAOImp

    AlumnoDAO <|.. AlumnoMockDAOImp
    ProfesorDAO <|.. ProfesorMockDAOImp
  }
}

' App solo arranca el controlador principal
App --> InstitutoController : empezar()

' InstitutoController crea y usa la vista
InstitutoController --> ViewController
ViewController --> ViewAlumno
ViewController --> ViewProfesor

' Vistas notifican al controlador
ViewAlumno --> InstitutoController
ViewProfesor --> InstitutoController

' InstitutoController usa la factoría, el modelo y los DAO (por interfaz) '
InstitutoController --> Instituto
InstitutoController --> InstitutoFactory
InstitutoController --> AlumnoDAO
InstitutoController --> ProfesorDAO

InstitutoFactory --> Modo
InstitutoFactory --> AlumnoDAO
InstitutoFactory --> ProfesorDAO

' Relaciones del modelo
Instituto "1" o-- "0..*" Alumno
Instituto "1" o-- "0..*" Profesor

@enduml
      
```