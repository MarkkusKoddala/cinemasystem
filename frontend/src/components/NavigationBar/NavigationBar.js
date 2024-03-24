import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import styles from './css/NavigationBar.module.css';
import apiService from "../../service/ApiService";
import {useError} from "../../contexts/ErrorContext";
import {useUser} from "../../contexts/UserContext"; // Import the CSS module

const NavigationBar = () => {
    const [users, setUsers] = useState([]);
    const {triggerError} = useError();
    const {setUser} = useUser();

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const data = await apiService.getUsers();
                setUsers(data);
                setUser(data[0])
            } catch (err){
                triggerError("VIGA: Klientide päring ebaõnnestus");
            }
        }

        fetchUsers();
    }, []);

    const handleUserSelection = (selectedUserId) => {
        const user = users.find(user => user.id.toString() === selectedUserId);

        if (user) {
            setUser(() => user);
        } else {
            triggerError("VIGA: Sellist klienti pole olemas");
        }
    };


    return (
        <nav className={styles.navbar}>
            <ul className={styles.navList}>
                <li className={styles.navItem}><Link to="/" className={styles.navLink}> Kinokava </Link></li>
            </ul>
            <div style={ {display: "flex"}}>
            <p style={{margin:"10px"}}> Vali klient: </p>
            <select className={styles.selectBox} onChange={(e) => handleUserSelection(e.target.value)}>
                {users.map( (user) =>
                    <option key={user.id} value={user.id}>{user.name}</option>
                )}
            </select>
            </div>
        </nav>
    );
};

export default NavigationBar;
