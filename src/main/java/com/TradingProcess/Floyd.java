package com.TradingProcess;

import com.Service.CurrencyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;

public class Floyd {
    final Logger logger = LoggerFactory.getLogger(Floyd.class);
    Double Min_profit;
    Double[][] rate;
    static Map<Integer,String> getName;

    public Floyd() {
    }

    public Floyd(Double min_profit, Double[][] rate, Map<Integer, String> getName) {
        Min_profit = min_profit;
        this.rate = rate;
        this.getName = getName;
    }

    public ArrayList<ProfitsResult> getPathProfits(){
        return getPathProfits(this.Min_profit,this.rate,this.getName);
    }

    private ArrayList<ProfitsResult> getPathProfits(Double min_profit,Double[][] rate,Map<Integer,String> getName){
        ArrayList<ProfitsResult> answer_profits = new ArrayList<>();
        int N = 175;
        int n = 12;
        logger.info("size : "+n);
        //int n = getName.size();
        Double[][][] profit = new Double[N][N][N];
        Integer[][][] path = new Integer[N][N][N];
        //Init array
        for(int i = 0; i < n;i++){
            for(int j = 0 ; j < n;j++){
                profit[1][i][j] = rate[i][j];
                if(rate[i][j]>0){
                    path[1][i][j] = j;
                }
            }
        }
        logger.info("pass init");
        //Arbitrage alogirthm
        for(int length = 2 ; length <=n ;length++){
            setarraytozero(n,profit[length]);
            for(int i = 0; i < n; i++){
                for(int j = 0 ; j < n;j++){
                    for(int k = 0 ; k < n;k++){
                        if(profit[length][i][j]<rate[i][k]*profit[length-1][k][j]){
                            profit[length][i][j] = rate[i][k]*profit[length-1][k][j];
                            path[length][i][j] = k;
                            if (profit[length][i][i]>Min_profit){
                                ProfitsResult temporary = new ProfitsResult(profit[length][i][i],length);
                                temporary = get_path_profit(i,i,length,path,temporary);
                                answer_profits.add(temporary);
                            }
                        }
                    }
                }
            }
        }
        return answer_profits;
    }
    private void setarraytozero(Integer n,Double arr[][]){
        for(int i = 0 ; i < n;i++){
            for(int j = 0 ; j < n;j++){
                arr[i][j] = 0.0;
            }
        }
    }

    private static ProfitsResult get_path_profit(int src,int destination,int length,Integer path[][][], ProfitsResult temporary_result){
        if(length == 0){
            //System.out.printf("%d : %f\n",src+1,profit);
            //temporary_result.arrayList_profits.add(src+1);
            temporary_result.setArrayList_profits(temporary_result.getArrayList_profits() +" -> "+ getName.get(src));
            return temporary_result;
        }else{
            //temporary_result.arrayList_profits.add(src+1);
            if(temporary_result.getArrayList_profits().equals("")){
                temporary_result.setArrayList_profits(getName.get(src));
            }else
                temporary_result.setArrayList_profits(temporary_result.getArrayList_profits() +" -> "+getName.get(src));
            return get_path_profit(path[length][src][destination],destination, length-1,path,temporary_result);
        }
    }
}
