// Contrato que obliga a todas las clases de personaje a exponer su acción de combate.
package com.gremio.modelo;

/**
 * Interfaz del modelo: define la mecánica de combate obligatoria para todo
 * aventurero. Permite invocar el comportamiento de forma polimórfica sin
 * instanceof ni casteos.
 */
public interface IAccionCombate {

    /**
     * Devuelve el mensaje oficial de la acción especial de la clase.
     *
     * @return texto de la acción, con el nombre del personaje ya sustituido
     */
    String ejecutarAccionCombate();
}
