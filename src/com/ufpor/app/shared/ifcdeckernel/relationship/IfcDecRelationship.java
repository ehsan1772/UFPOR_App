package com.ufpor.app.shared.ifcdeckernel.relationship;

import com.google.appengine.api.users.User;
import com.ufpor.app.server.ifcphysical.IfcFileObject;
import com.ufpor.app.shared.ifcdeckernel.IfcDecRoot;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import java.util.*;

/**
 * Created by Ehsan Barekati on 10/30/14.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class IfcDecRelationship<T extends IfcDecRoot, E extends IfcDecRoot> extends IfcDecRoot implements List<T> {
    //a delegate
//    @Persistent(defaultFetchGroup = "true")
//    protected ArrayList<T> list;
//    @Persistent(mappedBy = "childSpaces")
//    protected E owner;

    public abstract E getOwner();
    public abstract List<T> getList();

    public IfcDecRelationship(String GUID, User user, E owner) {
        super(GUID, user);
        initialize(owner);
    }

    public IfcDecRelationship(String GUID, User user) {
        super(GUID, user);
        initialize(null);
    }

    public IfcDecRelationship() {
        initialize(null);
    }

//    public IfcDecRoot getOwner() {
//        return owner;
//    }

//    public List<T> getList() {
//        return list;
//    }



    protected abstract void initialize(E owner);

//    {
//        getOwner() = owner;
//        list = new ArrayList<T>();
//    }

    @Override
    public int size() {
        return getList().size();
    }

    @Override
    public boolean isEmpty() {
        return getList().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getList().contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return getList().iterator();
    }

    @Override
    public Object[] toArray() {
        return getList().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return getList().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return getList().add(t);
    }

    @Override
    public boolean remove(Object o) {
        return getList().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return getList().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return getList().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return getList().addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return getList().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return getList().retainAll(c);
    }

    @Override
    public void clear() {
        getList().clear();
    }

    @Override
    public T get(int index) {
        return getList().get(index);
    }

    @Override
    public T set(int index, T element) {
        return getList().set(index, element);
    }

    @Override
    public void add(int index, T element) {
        getList().add(index, element);
    }

    @Override
    public T remove(int index) {
        return getList().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return getList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return getList().lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return getList().listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return getList().listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return getList().subList(fromIndex, toIndex);
    }

    @Override
    public ArrayList<IfcFileObject> getRelatedObjects() {
        ArrayList<IfcFileObject> results = new ArrayList<>();
        results.addAll(getList());
        return  results;
    }

}
