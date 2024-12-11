import mysql.connector
from mysql.connector import Error


#Diccionarios para conexion a alepo y a base de datos local
vet_santa_barbara = {
    'user': 'vetsb',
    'password':'A1b2c3d4e5F6',
    'host':'localhost',
    'port':3306,
    'database':'vet_santa_barbara',
    'autocommit':True
}


def bd():
    #Devuelve conexion a BD
    db = None
    try:
        db = mysql.connector.connect(**vet_santa_barbara)
    except Error as e:
        print("Error al establecer conexion con base de datos.",str(e))
    return db


def sql(consulta, filtros=None, unico=False):
    try:
        db = bd()
        cursor = db.cursor(dictionary=True)
        cursor.execute(consulta, filtros)
        rs = cursor.fetchone() if unico else cursor.fetchall()
        cursor.close()
        db.close()
        return rs
    except Exception as e:
        print(f"Error al ejecutar consulta {consulta} con valores {filtros}: {e}", flush=True)
        return []


def insertar_o_actualizar(consulta, valores):
    try:
        db = bd()
        cursor = db.cursor()
        cursor.execute(consulta, valores)
        id = cursor.lastrowid
        cursor.close()
        db.close()
        return id
    except Exception as e:
        print(f"Error al insertar {consulta} con valores {valores}: {e}", flush=True)
        return None
