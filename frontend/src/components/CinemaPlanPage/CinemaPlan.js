import React, { useState } from 'react';
import styles from './css/CinemaPlan.module.css';



//Kuvab kinosaali isteplaani

const CinemaPlan = ({cinemaHallPlan }) => {
    const { rows, cols, seats } = cinemaHallPlan;

    return (
        <div className={styles.cinemaContainer}>
            <div className={styles.screen}>Ekraan</div>
            <div className={styles.cinema}
                 style={{
                     gridTemplateRows: `repeat(${rows}, 30px)`,
                     gridTemplateColumns: `repeat(${cols}, 30px)`
                 }}
            >
                {seats.map((seat) => (
                    <div
                        key={seat.id}
                        className={`${styles.seat} ${styles[seat.status]} ${styles[seat.type]}`}
                        style={{gridRow: seat.row, gridColumn: seat.column}}
                    >
                        {seat.id}
                    </div>
                ))}
            </div>
        </div>
    );
};
export default CinemaPlan;