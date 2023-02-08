import axios from "axios";
import {useState, useEffect, useContext} from 'react';
import AuthContext from '../context/AuthProvider';

const useAxiosFetch = (dataUrl) => {
    const [data, setData] = useState([]);
    const { auth } = useContext(AuthContext);
    const config = {
        headers: {
            Authorization: `Bearer ${auth.accessToken}`
        }
    }

    useEffect(() => {
        const fetchData = async (dataUrl) => {
            const response = await axios.get(dataUrl, config);
            setData(response.data);
        }

        fetchData(dataUrl)
    }, [dataUrl]);

    return {data};
}

export default useAxiosFetch;