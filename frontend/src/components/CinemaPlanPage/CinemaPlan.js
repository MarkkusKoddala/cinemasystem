import React, { useState } from 'react';
import styles from './css/CinemaPlan.module.css';




const CinemaPlan = ({cinemaHallPlan }) => {
    const [selectedSeats, setSelectedSeats] = useState([]);
    const { rows, cols, seats } = cinemaHallPlan;


    console.log(cinemaHallPlan)
    const handleSeatClick = (seat) => {
        if (seat.status === 'available') {
            const newSelection = selectedSeats.includes(seat.id)
                ? selectedSeats.filter(id => id !== seat.id)
                : [...selectedSeats, seat.id];
            setSelectedSeats(newSelection);
        }
    };





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