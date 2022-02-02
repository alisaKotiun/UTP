package mySample;

import myInterface.IAggregable;
import myInterface.IContainer;
import myInterface.IDeeplyCloneable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Container <TElement extends IAggregable<TElement, TResult> & IDeeplyCloneable<TElement>, TResult> implements IContainer<TElement, TResult> {

    private List<TElement> cont;

    public Container(){
        cont = new ArrayList<>();
    }

    public void addEl(TElement el){
        cont.add(el);
    }

    public TElement getElement(int ind){
        if(ind < 0 || ind>=cont.size()){
            return null;
        }
        return cont.get(ind);
    }

    @Override
    public List<TElement> elements() {
        return cont;
    }

    @Override
    public TResult aggregateAllElements() {
        TResult res = null;
        Collections.reverse(cont);

        for (TElement e : cont) {
            res = e.aggregate(res);
        }
        return res;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        TElement clone;
        if(index < 0 || index>=cont.size()){
            return null;
        }
        clone = cont.get(index).deepClone();
        return clone;
    }
}
