import React, {createContext, useContext, useState} from 'react';

const UserContext = createContext();
export const useUser = () => useContext(UserContext);

//Provider, mis hoiab kasutaja andmeid, kes hetkel veebilehel justkui aktiivne
export const UserProvider = ({ children }) => {
    const [user, setUser] = useState({
        id: null,
        name: "",
        hasShowTimesBeforeNow: false
    });

    return (
        <UserContext.Provider value={{ user, setUser }}>
            {children}
        </UserContext.Provider>
    );
};
