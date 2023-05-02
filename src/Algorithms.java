import java.util.*;

public class Algorithms {
    Initialization init;
    private int num;

    public Algorithms(Initialization init){
        this.init = init;
        this.num = 0;
    }

    public int SearchCost(Character C,boolean Diagonal){
        if (C == 'D') return 1;
        if (C == 'R') return 3;
        if (C == 'H' && !Diagonal) return 5;
        if (C == 'H' && Diagonal) return 10;
        if (C == 'S') return 0;
        if (C == 'G') return 5;
        return -1;
    }
    public boolean CheckXY(Hashtable<Node, String> VisitedNodesTable,int x,int y){
        Enumeration<Node> e = VisitedNodesTable.keys();
        while (e.hasMoreElements()) {
            // Getting the key of a particular entry
            if(e.nextElement().CheckXY(x,y)) return true;
        }
        return false;
    }
    public boolean CheckXYNode(Node Curr, int x, int y){
        if (Curr != null){
            return Curr.CheckXY(x, y);
        }
        return false;
    }
    public List<Node> FindChildren(Character[][] matrix, int sizeMatrix, Node dad, boolean direction, Node goal) {
        int x = dad.getX();
        int y = dad.getY();
        Node DadsDad = dad.getDad();
        List<Node> children = new ArrayList<>();


        if (y + 1 <= sizeMatrix && !matrix[x - 1][y].equals('X') && !matrix[x - 1][y].equals('S') && !CheckXYNode(DadsDad,x,(y + 1))) {
            children.add(new Node(x, y + 1, dad, "R", SearchCost(matrix[x - 1][y], false)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x, y + 1)) {
                return children;
            }
        }

        if (x + 1 <= sizeMatrix && y + 1 <= sizeMatrix && !matrix[x][y].equals('X') && !matrix[x][y].equals('S') && !CheckXYNode(DadsDad,x +1,(y + 1))) {
                children.add(new Node(x + 1, y + 1, dad, "RD", SearchCost(matrix[x][y], true)));
                children.get(children.size()-1).setG(dad.getG());
                children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x + 1, y + 1)) {
                    return children;
                }
        }
        if (x + 1 <= sizeMatrix && !matrix[x][y - 1].equals('X') && !matrix[x][y - 1].equals('S') && !CheckXYNode(DadsDad,x +1,y)) {
            children.add(new Node(x + 1, y, dad, "D", SearchCost(matrix[x][y - 1], false)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x + 1, y)) {
                return children;
            }
        }

        if (x + 1 <= sizeMatrix && y - 1 > 0 && !matrix[x][y - 2].equals('X') && !matrix[x][y - 2].equals('S') && !CheckXYNode(DadsDad,x+1,y-1)) {
            children.add(new Node(x + 1, y - 1, dad, "LD", SearchCost(matrix[x][y - 2], true)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x + 1, y - 1)) {
                return children;
            }
        }

        if (y - 1 > 0 && !matrix[x - 1][y - 2].equals('X') && !matrix[x - 1][y - 2].equals('S') && !CheckXYNode(DadsDad,x,y-1)) {
            children.add(new Node(x, y - 1, dad, "L", SearchCost(matrix[x - 1][y - 2], false)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x, y - 1)) {
                return children;
            }
        }

        if (y - 1 > 0 && x - 1 > 0 && !matrix[x - 2][y - 2].equals('X') && !matrix[x - 2][y - 2].equals('S') && !CheckXYNode(DadsDad,x-1,y-1)) {
            children.add(new Node(x - 1, y - 1, dad, "LU", SearchCost(matrix[x - 2][y - 2], true)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x - 1, y - 1)) {
                return children;
            }
        }
        if (x - 1 > 0 && !matrix[x - 2][y - 1].equals('X') && !matrix[x - 2][y - 1].equals('S') && !CheckXYNode(DadsDad,x-1,y)) {
            children.add(new Node(x - 1, y, dad, "LU", SearchCost(matrix[x - 2][y - 1], true)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x - 1, y)) {
                return children;
            }
        }
        if (x - 1 > 0 && y + 1 <= sizeMatrix && !matrix[x - 2][y].equals('X') && !matrix[x - 2][y].equals('S') && !CheckXYNode(DadsDad,x-1,y+1)) {
            children.add(new Node(x - 1, y + 1, dad, "LU", SearchCost(matrix[x - 2][y], true)));
            children.get(children.size()-1).setG(dad.getG());
            children.get(children.size()-1).setF(CulcHeuristic(children.get(children.size()-1),goal));
            if (goal.CheckXY(x - 1, y + 1)) {
                return children;
            }
        }
        if (direction){
            return children;
        }else {
            if (!children.isEmpty()){
                List<Node> reverse = new ArrayList<>(children.subList(1,children.size()));
                Collections.reverse(reverse);
                reverse.add(0,children.get(0));
                return reverse;
            }
        }
        return children;
    }

    public List<Node> FindChildren2(Character[][] matrix,int SizeMatrix,Node Dad,boolean Direction,Node Goal){
        int x = Dad.getX();
        int y = Dad.getY();
        int xDad =-2;
        int yDad =-2;
        if (Dad.getDad()!=null) {
            xDad = Dad.getDad().getX();
            yDad = Dad.getDad().getY();
        }

        List<Node> Children = new ArrayList<>();
        if (Direction){
            if(y+1<=SizeMatrix) {
                if(!matrix[x-1][y].equals('X')  && !(x == xDad && (y+1) == yDad) ) {
                    Children.add(new Node(x, y + 1,Dad,"R",SearchCost(matrix[x-1][y],false)));
                    if (Goal.CheckXY(x,y+1)){
                        return Children;
                    }
                }
                if(x+1 <=SizeMatrix){
                    if(!matrix[x][y].equals('X')  && !((x+1) == xDad && (y+1) == yDad)){
                        Children.add(new Node(x+1,y+1,Dad,"RD",SearchCost(matrix[x][y],true)));
                        if (Goal.CheckXY(x+1,y+1)){
                            return Children;
                        }
                    }
                }
            }
            if(x+1 <=SizeMatrix){
                if(!matrix[x][y-1].equals('X')  && !((x+1) == xDad && (y) == yDad)){
                    Children.add(new Node(x+1,y,Dad,"D",SearchCost(matrix[x][y-1],false)));
                    if (Goal.CheckXY(x+1,y)){
                        return Children;
                    }
                }
                if(y-1 >0){
                    if(!matrix[x][y-2].equals('X') && !((x+1) == xDad && (y-1) == yDad)){
                        Children.add(new Node(x+1,y-1,Dad,"LD",SearchCost(matrix[x][y-2],true)));
                        if (Goal.CheckXY(x+1,y-1)){
                            return Children;
                        }
                    }
                }
            }
            if(y-1 >0){
                if(!matrix[x-1][y-2].equals('X') && !((x) == xDad && (y-1) == yDad)){
                    Children.add(new Node(x,y-1,Dad,"L",SearchCost(matrix[x-1][y-2],false)));
                    if (Goal.CheckXY(x,y-1)){
                        return Children;
                    }
                }
                if(x-1>0){
                    if(!matrix[x-2][y-2].equals('X') && !((x-1) == xDad && (y-1) == yDad)){
                        Children.add(new Node(x-1,y-1,Dad,"LU",SearchCost(matrix[x-2][y-2],true)));
                        if (Goal.CheckXY(x-1,y-1)){
                            return Children;
                        }
                    }
                }
            }
            if(x-1 > 0){
                if(!matrix[x-2][y-1].equals('X') && !((x-1) == xDad && (y) == yDad)){
                    Children.add(new Node(x-1,y,Dad,"U",SearchCost(matrix[x-2][y-1],false)));
                    if (Goal.CheckXY(x-1,y)){
                        return Children;
                    }
                }
                if (y+1 <=SizeMatrix){
                    if(!matrix[x-2][y].equals('X') && !((x-1) == xDad && (y+1) == yDad)) {
                        Children.add(new Node(x-1,y+1,Dad,"RU",SearchCost(matrix[x-2][y],true)));
                        if (Goal.CheckXY(x-1,y+1)){
                            return Children;
                        }
                    }
                }
            }
        }
        else{

        }
        return Children;
    }

    public void findParameters(Node End){
        Node Dad = End.getDad();
        int countCost = End.getCost();
        String where = End.getDirection();
        while (Dad!=null){
            countCost+=Dad.getCost();
            where = Dad.getDirection() +"-"+where;
            Dad = Dad.getDad();
        }
        System.out.println("cost: " + End.getG());
        System.out.println("dir: " + where);
        System.out.println("num: "+this.num);
    }
    public boolean CheckTableNodes(Hashtable<Node, Integer> NodesTable, Node curr){
        Enumeration<Node> e = NodesTable.keys();
        while (e.hasMoreElements()) {

            // Getting the key of a particular entry
            Node key = e.nextElement();
            if (key.equals(curr)){
                return true;
            }
        }
        return false;
    }
    public boolean BFS(Node Start, Node Goal){
        this.num = 0;
        Hashtable<Node, Integer> VisitedNodesTable = new Hashtable<>();
        Queue<Node> NotVisitedNodesQueue = new LinkedList<>();
        NotVisitedNodesQueue.add(Start);
        while (!NotVisitedNodesQueue.isEmpty()){
            Node CurrNode = NotVisitedNodesQueue.remove();
            VisitedNodesTable.put(CurrNode,CurrNode.getCost());
            List<Node> Children = FindChildren(init.matrix,init.getSizeMatrix(),CurrNode, init.getClockwise(),Goal);
            this.num+=Children.size();
            for (int i = 0; i < Children.size(); i++) {
                if(!VisitedNodesTable.containsKey(Children.get(i)) && !NotVisitedNodesQueue.contains(Children.get(i))){
                    if (Goal.equals(Children.get(i))){
                        findParameters(Children.get(i));
                        return true;
                    }
                    NotVisitedNodesQueue.add(Children.get(i));
                }
            }
        }
        return false;
    }
    public String Limited_DFS(Node Curr, Node Goal, int limit, Hashtable<Node, String> VisitedNodesTable){
        boolean isCutoff = false;
        String result = "";
        if (Goal.equals(Curr)){
            findParameters(Curr);
            return "yay";
        } else if (limit ==0) {
            return "cutoff";
        } else{
            VisitedNodesTable.put(Curr,"2");
            List<Node> Children = FindChildren(init.matrix,init.getSizeMatrix(),Curr, init.getClockwise(),Goal);
            this.num += Children.size();
            for (int i = 0; i < Children.size(); i++) {
                if (!VisitedNodesTable.containsKey(Children.get(i))){
                    result = Limited_DFS(Children.get(i),Goal,limit-1,VisitedNodesTable);
                    if (result == "cutoff"){
                        isCutoff = true;
                    } else if (result != "fail") {
                        return result;
                    }
                }
            }
            VisitedNodesTable.remove(Curr);
            if (isCutoff){
                return "cutoff";
            }else {
                return "fail";
            }

        }
    }
    public String DFID(Node Start, Node Goal){
        String result = "";
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            this.num =0;
            Hashtable<Node, String> VisitedNodesTable = new Hashtable<>();
            result =Limited_DFS(Start,Goal,i,VisitedNodesTable);
            if (result != "cutoff"){
                return result;
            }
        }
        return "fail";
    }

    private int CulcHeuristic(Node Start, Node End){
        return (Math.abs(Start.getX() - End.getX()) + Math.abs(Start.getY() - End.getY()));
    }

    public Boolean Astar(Node Start, Node Goal) {
        PriorityQueue<Node> NotVisitedNodesQueue = new PriorityQueue<>();
        Hashtable<Node, Integer> VisitedNodesTable = new Hashtable<>();
        Hashtable<Node, Integer> NotVisitedNodesTable = new Hashtable<>();
        this.num =0;
        Start.setG(0);
        Start.setF(CulcHeuristic(Start,Goal));
        NotVisitedNodesTable.put(Start,Start.getF());
        NotVisitedNodesQueue.add(Start);
        while (!NotVisitedNodesQueue.isEmpty()) {
            Node Curr = NotVisitedNodesQueue.poll();
            NotVisitedNodesTable.remove(Curr);
            if (Goal.equals(Curr)) {
                findParameters(Curr);
                return true;
            }
            VisitedNodesTable.put(Curr, Curr.getF());
            List<Node> Children = FindChildren(init.matrix, init.getSizeMatrix(), Curr, init.getClockwise(), Goal);
            this.num += Children.size();
            for (int i = 0; i < Children.size(); i++) {
                if (!CheckTableNodes(VisitedNodesTable, Children.get(i)) && !NotVisitedNodesQueue.contains(Children.get(i))) {
                    NotVisitedNodesQueue.add(Children.get(i));
                    NotVisitedNodesTable.put(Children.get(i),Children.get(i).getF());
                } else if (NotVisitedNodesTable.containsKey(Children.get(i))) {
                    int NewF = Children.get(i).getF();
                    int OldF = NotVisitedNodesTable.get(Children.get(i));
                    if (NewF < OldF){
                        NotVisitedNodesQueue.remove(Children.get(i));
                        NotVisitedNodesTable.remove(Children.get(i));
                        NotVisitedNodesTable.put(Children.get(i),NewF);
                        NotVisitedNodesQueue.add(Children.get(i));
                    }
                }
            }
        }
        return false;

    }

    public boolean IDAStar(Node Start, Node Goal){
        Stack<Node> NotVisitedNodesStack = new Stack<>();
        Hashtable<Node, String> VisitedNodesTable = new Hashtable<>();
        Hashtable<Node, String[]> NotVisitedNodesTable = new Hashtable<>();
        this.num =0;
        Start.setG(0);
        Start.setF(CulcHeuristic(Start,Goal));
        int t = CulcHeuristic(Start,Goal);
        while (t != Integer.MAX_VALUE){
            int minF = Integer.MAX_VALUE;
            NotVisitedNodesTable.put(Start,new String[] {"not out",String.valueOf(Start.getF())});
            NotVisitedNodesStack.add(Start);
            while (!NotVisitedNodesStack.isEmpty()){
                Node Curr = NotVisitedNodesStack.pop();
                if (Objects.equals((NotVisitedNodesTable.get(Curr))[0], "out")){
                    NotVisitedNodesTable.remove(Curr);
                }else {
                    NotVisitedNodesTable.put(Curr,new String[] {"out",String.valueOf(Curr.getF())});
                    NotVisitedNodesStack.push(Curr);
                    List<Node> Children = FindChildren(init.matrix, init.getSizeMatrix(), Curr, init.getClockwise(), Goal);
                    this.num += Children.size();
                    for (int i = 0; i < Children.size(); i++) {
                        if (Children.get(i).getF() > t){
                            minF = Math.min(minF,Children.get(i).getF());
                            continue;
                        }
                        if (NotVisitedNodesTable.containsKey(Children.get(i))){
                            if ((NotVisitedNodesTable.get(Children.get(i)))[0] != "out"){
                                int OldF =Integer.parseInt(NotVisitedNodesTable.get(Children.get(i))[1]);
                                int NewF = Children.get(i).getF();
                                if (NewF < OldF){
                                    NotVisitedNodesTable.remove(Children.get(i));
                                    NotVisitedNodesStack.remove(Children.get(i));
                                } else {continue;}
                            } else {continue;}
                        }
                        if (Goal.equals(Children.get(i))) {
                            findParameters(Children.get(i));
                            return true;
                        }
                        NotVisitedNodesTable.put(Children.get(i),new String[] {"not out",String.valueOf(Children.get(i).getF())});
                        NotVisitedNodesStack.add(Children.get(i));

                    }
                }
            }
            t= minF;
        }
        return false;
    }

    public boolean DFDnB(Node Start, Node Goal){
        Stack<Node> NotVisitedNodesStack = new Stack<>();
        Hashtable<Node, String> VisitedNodesTable = new Hashtable<>();
        Hashtable<Node, String[]> NotVisitedNodesTable = new Hashtable<>();
        this.num =0;
        Start.setG(0);
        Start.setF(CulcHeuristic(Start,Goal));
        Node result = null;
        int t = Integer.MAX_VALUE;
        NotVisitedNodesTable.put(Start,new String[] {"not out",String.valueOf(Start.getF())});
        NotVisitedNodesStack.add(Start);
        while (!NotVisitedNodesStack.isEmpty()){
            Node Curr = NotVisitedNodesStack.pop();
            if (Objects.equals(NotVisitedNodesTable.get(Curr)[0], "out")){
                NotVisitedNodesTable.remove(Curr);
            }else {
                NotVisitedNodesTable.put(Curr,new String[] {"out",String.valueOf(Curr.getF())});
                NotVisitedNodesStack.push(Curr);
                List<Node> Children = FindChildren(init.matrix, init.getSizeMatrix(), Curr, init.getClockwise(), Goal);
                List<Node> N = new ArrayList<>();
                this.num += Children.size();
                Children.sort(Node::compareTo);
                List<Integer> IndexCutOff = new ArrayList<>();
                for (int i = 0; i < Children.size(); i++) {
                    if (Children.get(i).getF() >= t){
                        for (int j = i; j < Children.size(); j++) {
                            IndexCutOff.add(j);
                        }
                        break;
                    }else if (NotVisitedNodesTable.containsKey(Children.get(i))){
                        if (!Objects.equals((NotVisitedNodesTable.get(Children.get(i)))[0], "out")){
                            int OldF =Integer.parseInt(NotVisitedNodesTable.get(Children.get(i))[1]);
                            int NewF = Children.get(i).getF();
                            if (OldF > NewF){
                                NotVisitedNodesTable.remove(Children.get(i));
                                NotVisitedNodesStack.remove(Children.get(i));
                            } else {
                                IndexCutOff.add(i);
                            }
                        } else {
                            IndexCutOff.add(i);
                        }
                    } else if (Goal.equals(Children.get(i))) {
                        result = Children.get(i);
                        t = result.getF();
                        for (int j = i; j < Children.size(); j++) {
                            IndexCutOff.add(j);
                        }
                        break;
                    }

                }
                for (int i = Children.size() - 1; i >= 0; i--) {
                    if (!IndexCutOff.contains(i)) {
                        NotVisitedNodesTable.put(Children.get(i), new String[]{"not out", String.valueOf(Curr.getF())});
                        NotVisitedNodesStack.push(Children.get(i));
                    }
                }
            }
        }
        if (result != null) {
            findParameters(result);
            return true;
        }
        return false;
    }



}
