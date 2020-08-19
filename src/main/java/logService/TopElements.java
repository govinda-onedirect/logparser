package logService;

import dtos.API;

import java.util.HashMap;
import java.util.Map;


public class TopElements {
    int currentCount =0;
    final int size;
    private final Map<Integer,API> frequentItems;
    private final Map<String,Integer> keyIndex;

    public TopElements(int size) {
        this.size = size;
        frequentItems = new HashMap<>(size);
        keyIndex = new HashMap<>(size);
    }


    public Map<Integer,API> getFrequentItems() {
        return frequentItems;
    }

    public void fill(API api) {
        int index = keyIndex.getOrDefault(api.getAPIKey(),frequentItems.size());
        if (index < size) {
            frequentItems.put(index, api);
            keyIndex.put(api.getAPIKey(),index);
        }
        maxiFy(api, index);
    }

    private void maxiFy(API api, int index){
        if (index==size && frequentItems.get(index-1).getMetadata().getFrequency()<api.getMetadata().getFrequency()){
            frequentItems.put(index,api);
            keyIndex.put(api.getAPIKey(),index-1);
        }
        for (int i = index-2; i >=0; i--) {
            if (frequentItems.get(i).getMetadata().getFrequency()<api.getMetadata().getFrequency()){
                swap(i);
            }
        }

    }

    private void swap(int index){
        API temp1 = frequentItems.get(index+1);
        API temp2 =frequentItems.get(index);
        keyIndex.put(temp1.getAPIKey(),index);
        keyIndex.put(temp2.getAPIKey(),index+1);
        frequentItems.put(index+1,temp2);
        frequentItems.put(index,temp1);
    }
}
