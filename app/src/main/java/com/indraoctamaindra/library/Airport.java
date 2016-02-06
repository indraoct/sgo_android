package com.indraoctamaindra.library;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Indra Octama on 7/12/2015.
 */
public class Airport {

    /*
    *data private  airports
     */

    public JSONObject JSONAirport(){
        String dataAirport = "{\"AIRPORTS\":[{\"code\":\"AMQ\",\"country\":\"INDONESIA\",\"city\":\"AMBON\",\"name\":\"AMBON PATTIMURA\",\"desc\":null},{\"code\":\"ARD\",\"country\":\"INDONESIA\",\"city\":\"ALOR ISLAND\",\"name\":\"ALOR ISLAND, MALI AIRPORT\",\"desc\":null},{\"code\":\"BDJ\",\"country\":\"INDONESIA\",\"city\":\"BANJARMASIN\",\"name\":\"BANJARMASIN SJAMSUDIN\",\"desc\":null},{\"code\":\"BDO\",\"country\":\"INDONESIA\",\"city\":\"BANDUNG\",\"name\":\"BANDUNG HUSEIN\",\"desc\":null},{\"code\":\"BEJ\",\"country\":\"INDONESIA\",\"city\":\"BERAU\",\"name\":\"BERAU\",\"desc\":null},{\"code\":\"BIK\",\"country\":\"INDONESIA\",\"city\":\"BIAK\",\"name\":\"BIAK MOKMER\",\"desc\":null},{\"code\":\"BJW\",\"country\":\"INDONESIA\",\"city\":\"NTT\",\"name\":\"BAJAWA\",\"desc\":null},{\"code\":\"BKS\",\"country\":\"INDONESIA\",\"city\":\"BENGKULU\",\"name\":\"BENGKULU PADANGKEM\",\"desc\":null},{\"code\":\"BMU\",\"country\":\"INDONESIA\",\"city\":\"BIMA\",\"name\":\"BIMA\",\"desc\":null},{\"code\":\"BPN\",\"country\":\"INDONESIA\",\"city\":\"BALIKPAPAN\",\"name\":\"BALIKPAPAN SEPINGAN\",\"desc\":null},{\"code\":\"BTH\",\"country\":\"INDONESIA\",\"city\":\"BATAM\\/BATU\",\"name\":\"BATAM\\/BATU BESAR\",\"desc\":null},{\"code\":\"BTJ\",\"country\":\"INDONESIA\",\"city\":\"BANDA\",\"name\":\"BANDA ACEH B.BINTANG\",\"desc\":null},{\"code\":\"BUW\",\"country\":\"INDONESIA\",\"city\":\"BAU BAU\",\"name\":\"BAU BAU\",\"desc\":null},{\"code\":\"BXT\",\"country\":\"INDONESIA\",\"city\":\"BONTANG\",\"name\":\"BONTANG\",\"desc\":null},{\"code\":\"CGK\",\"country\":\"INDONESIA\",\"city\":\"JAKARTA\",\"name\":\"JAKARTA SOEKARNO HATTA\",\"desc\":null},{\"code\":\"DJB\",\"country\":\"INDONESIA\",\"city\":\"JAMBI\",\"name\":\"JAMBI SYARIFUDIN\",\"desc\":null},{\"code\":\"DJJ\",\"country\":\"INDONESIA\",\"city\":\"JAYAPURA\",\"name\":\"JAYAPURA SENTANI\",\"desc\":null},{\"code\":\"DPS\",\"country\":\"INDONESIA\",\"city\":\"DENPASAR\",\"name\":\"DENPASAR BALI NGURAH RAI\",\"desc\":null},{\"code\":\"ENE\",\"country\":\"INDONESIA\",\"city\":\"ENDE\",\"name\":\"ENDE\",\"desc\":null},{\"code\":\"GNS\",\"country\":\"INDONESIA\",\"city\":\"NIAS\",\"name\":\"NIAS, GUNUNG SITOLI\",\"desc\":null},{\"code\":\"GTO\",\"country\":\"INDONESIA\",\"city\":\"GORONTALO\",\"name\":\"GORONTALO TOLOTIO\",\"desc\":null},{\"code\":\"HLP\",\"country\":\"INDONESIA\",\"city\":\"JAKARTA HALIM\",\"name\":\"HALIM\",\"desc\":null},{\"code\":\"JOG\",\"country\":\"INDONESIA\",\"city\":\"YOGYAKARTA\",\"name\":\"YOGYAKARTA ADISUTJIPTO\",\"desc\":null},{\"code\":\"KBU\",\"country\":\"INDONESIA\",\"city\":\"KOTA BARU\",\"name\":\"KOTA BARU\",\"desc\":null},{\"code\":\"KDI\",\"country\":\"INDONESIA\",\"city\":\"KENDARI\",\"name\":\"KENDARI MONGINSIDI\",\"desc\":null},{\"code\":\"KOE\",\"country\":\"INDONESIA\",\"city\":\"KUPANG\",\"name\":\"KUPANG\",\"desc\":null},{\"code\":\"KTG\",\"country\":\"INDONESIA\",\"city\":\"KETAPANG\",\"name\":\"KETAPANG\",\"desc\":null},{\"code\":\"LBJ\",\"country\":\"INDONESIA\",\"city\":\"LABUANBAJO\",\"name\":\"LABUANBAJO\",\"desc\":null},{\"code\":\"LOP\",\"country\":\"INDONESIA\",\"city\":\"MATARAM\",\"name\":\"MATARAM SELAPARANG\",\"desc\":null},{\"code\":\"LSW\",\"country\":\"INDONESIA\",\"city\":\"LHOKSEUMAWE\",\"name\":\"LHOKSEUMAWE\",\"desc\":null},{\"code\":\"LUW\",\"country\":\"INDONESIA\",\"city\":\"LUWUK\",\"name\":\"LUWUK\",\"desc\":null},{\"code\":\"MDC\",\"country\":\"INDONESIA\",\"city\":\"MANADO\",\"name\":\"SAMRATULANGI\",\"desc\":null},{\"code\":\"MES\",\"country\":\"INDONESIA\",\"city\":\"MEDAN\",\"name\":\"MEDAN POLONIA\",\"desc\":null},{\"code\":\"MJU\",\"country\":\"INDONESIA\",\"city\":\"MAMUJU\",\"name\":\"MAMUJU\",\"desc\":null},{\"code\":\"MKQ\",\"country\":\"INDONESIA\",\"city\":\"MERAUKE\",\"name\":\"MERAUKE\",\"desc\":null},{\"code\":\"MKW\",\"country\":\"INDONESIA\",\"city\":\"MANOKWARI\",\"name\":\"MANOKWARI RENDANI\",\"desc\":null},{\"code\":\"MLG\",\"country\":\"INDONESIA\",\"city\":\"MALANG\",\"name\":\"MALANG ABD RAKHMAN SALEH\",\"desc\":null},{\"code\":\"MOF\",\"country\":\"INDONESIA\",\"city\":\"MAUMERE\",\"name\":\"MAUMERE\",\"desc\":null},{\"code\":\"NNX\",\"country\":\"INDONESIA\",\"city\":\"NUNUKAN\",\"name\":\"NUNUKAN\",\"desc\":null},{\"code\":\"PDG\",\"country\":\"INDONESIA\",\"city\":\"PADANG\",\"name\":\"PADANG TABING\",\"desc\":null},{\"code\":\"PGK\",\"country\":\"INDONESIA\",\"city\":\"PANGKALPINANG\",\"name\":\"PANGKALPINANG\",\"desc\":null},{\"code\":\"PKN\",\"country\":\"INDONESIA\",\"city\":\"PANGKALANBUN\",\"name\":\"PANGKALANBUN\",\"desc\":null},{\"code\":\"PKU\",\"country\":\"INDONESIA\",\"city\":\"PEKANBARU\",\"name\":\"PEKANBARU SIMPANG\",\"desc\":null},{\"code\":\"PKY\",\"country\":\"INDONESIA\",\"city\":\"PALANGKARAYA\",\"name\":\"TJILIK RIWUT\",\"desc\":null},{\"code\":\"PLM\",\"country\":\"INDONESIA\",\"city\":\"PALEMBANG\",\"name\":\"BADARUDDIN\",\"desc\":null},{\"code\":\"PLW\",\"country\":\"INDONESIA\",\"city\":\"PALU\",\"name\":\"MUTIARA\",\"desc\":null},{\"code\":\"PNK\",\"country\":\"INDONESIA\",\"city\":\"PONTIANAK\",\"name\":\"PONTIANAK\",\"desc\":null},{\"code\":\"PSU\",\"country\":\"INDONESIA\",\"city\":\"PUTUSSIBAU\",\"name\":\"PUTUSSIBAU\",\"desc\":null},{\"code\":\"RTG\",\"country\":\"INDONESIA\",\"city\":\"RUTENG\",\"name\":\"RUTENG\",\"desc\":null},{\"code\":\"SAU\",\"country\":\"INDONESIA\",\"city\":\"PULAU SAWU\",\"name\":\"SAWU\",\"desc\":null},{\"code\":\"SBQ\",\"country\":\"INDONESIA\",\"city\":\"SIBOLGA\",\"name\":\"SIBOLGA\",\"desc\":null},{\"code\":\"SMQ\",\"country\":\"INDONESIA\",\"city\":\"SAMPIT\",\"name\":\"SAMPIT\",\"desc\":null},{\"code\":\"SNX\",\"country\":\"INDONESIA\",\"city\":\"SINABANG\",\"name\":\"SINABANG\",\"desc\":null},{\"code\":\"SOC\",\"country\":\"INDONESIA\",\"city\":\"SOLO\",\"name\":\"SOLO\",\"desc\":null},{\"code\":\"SOQ\",\"country\":\"INDONESIA\",\"city\":\"SORONG\",\"name\":\"SORONG\",\"desc\":null},{\"code\":\"SQG\",\"country\":\"INDONESIA\",\"city\":\"SINTANG\",\"name\":\"SINTANG\",\"desc\":null},{\"code\":\"SRG\",\"country\":\"INDONESIA\",\"city\":\"SEMARANG\",\"name\":\"SEMARANG\",\"desc\":null},{\"code\":\"SRI\",\"country\":\"INDONESIA\",\"city\":\"SAMARINDA\",\"name\":\"SAMARINDA\",\"desc\":null},{\"code\":\"SUB\",\"country\":\"INDONESIA\",\"city\":\"SURABAYA\",\"name\":\"SURABAYA JUANDA\",\"desc\":null},{\"code\":\"SWQ\",\"country\":\"INDONESIA\",\"city\":\"SUMBAWA\",\"name\":\"SUMBAWA\",\"desc\":null},{\"code\":\"TIM\",\"country\":\"INDONESIA\",\"city\":\"TIMIKA\",\"name\":\"TIMIKA\",\"desc\":null},{\"code\":\"TJQ\",\"country\":\"INDONESIA\",\"city\":\"TANJUNG PANDAN\",\"name\":\"TANJUNG PANDAN\",\"desc\":null},{\"code\":\"TKG\",\"country\":\"INDONESIA\",\"city\":\"BANDAR LAMPUNG\",\"name\":\"BANDAR LAMPUNG BRANTI\",\"desc\":null},{\"code\":\"TMC\",\"country\":\"INDONESIA\",\"city\":\"TAMBOLAKA\",\"name\":\"TAMBOLAKA\",\"desc\":null},{\"code\":\"TNJ\",\"country\":\"INDONESIA\",\"city\":\"TANJUNG PINANG\",\"name\":\"TANJUNG PINANG\",\"desc\":null},{\"code\":\"TRK\",\"country\":\"INDONESIA\",\"city\":\"TARAKAN\",\"name\":\"TARAKAN\",\"desc\":null},{\"code\":\"TTE\",\"country\":\"INDONESIA\",\"city\":\"TERNATE\",\"name\":\"TERNATE\",\"desc\":null},{\"code\":\"UPG\",\"country\":\"INDONESIA\",\"city\":\"MAKASSAR\",\"name\":\"UJUNG PANDANG HASANUDIN\",\"desc\":null},{\"code\":\"WGP\",\"country\":\"INDONESIA\",\"city\":\"WAINGAPU\",\"name\":\"WAINGAPU\",\"desc\":null},{\"code\":\"WMX\",\"country\":\"INDONESIA\",\"city\":\"WAMENA\",\"name\":\"WAMENA\",\"desc\":null},{\"code\":\"SIN\",\"country\":\"SINGAPORE\",\"city\":\"SINGAPORE\",\"name\":\"CHANG I\",\"desc\":null},{\"code\":\"KUL\",\"country\":\"Malaysia\",\"city\":\"Kuala Lumpur\",\"name\":\"KUALA LUMPUR\",\"desc\":null}]}";

        JSONObject jsonAirport = null;
        try {
            jsonAirport = new JSONObject(dataAirport);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonAirport;
    }

    public Map ArrAirport(){

        Map<String, String> arrAirport = new HashMap<String, String>();

        try{

            JSONArray array = JSONAirport().getJSONArray("AIRPORTS");
            for(int i = 0 ; i < array.length() ; i++){

                arrAirport.put(array.getJSONObject(i).getString("code"), array.getJSONObject(i).getString("name"));

            }


        }catch (Exception e){

            e.printStackTrace();
        }
        return arrAirport;
    }

    public List CodeAirport(){

        List<String> codeAirport = new ArrayList<String>();

        try{
            JSONArray array = JSONAirport().getJSONArray("AIRPORTS");
            for(int i = 0 ; i < array.length() ; i++){

                codeAirport.add(array.getJSONObject(i).getString("code"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return codeAirport;
    }

    public List NameAirport(){

        List nameAirport = new ArrayList<String>();

        try{
            JSONArray array = JSONAirport().getJSONArray("AIRPORTS");
            for(int i = 0 ; i < array.length() ; i++){

                nameAirport.add(array.getJSONObject(i).getString("name"));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return nameAirport;
    }


    public void test(){

        Map<Integer,Map<String,Map<Integer,Map<String,String>>>> arrRakatokan = new HashMap<>();


    }



}









