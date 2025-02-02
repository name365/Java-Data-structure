package github2;

public class SingleLinkedListTest {

	public static void main(String[] args) {
		// 测试一下
		// 1.创建节点
		HeroNode hero = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
		HeroNode hero5 = new HeroNode(5, "关胜", "大刀");
		HeroNode hero6 = new HeroNode(6, "林冲", "豹子头");
		HeroNode hero7 = new HeroNode(7, "秦明", "霹雳火");

		// 创建一个链表
		SingleLinkedList singk = new SingleLinkedList();

		// 添加按照编号的顺序
		singk.addByOrder(hero);
		singk.addByOrder(hero7);
		singk.addByOrder(hero5);

		// 创建另一个链表
		SingleLinkedList singk2 = new SingleLinkedList();

		// 添加按照编号的顺序
		singk2.addByOrder(hero3);
		singk2.addByOrder(hero6);
		singk2.addByOrder(hero2);
		singk2.addByOrder(hero4);

		// 显示链表
		singk.list();
		System.out.println("------------------");
		singk2.list();

		//合并后的
		System.out.println("合并后的:");
		SingleLinkedList singk3 = singk.merge(singk, singk2);
        singk3.list();
	}

}

// 定义一个SingleLinkedList类,来管理我们的英雄
class SingleLinkedList {
	// 先初始化一个头节点,头节点不要动(防止后期找不到此链表),不存放具体数据
	private HeroNode head = new HeroNode(0, "", "");

	public SingleLinkedList(HeroNode head) {
		this.head = head;
	}
	
	public SingleLinkedList() {
        head = new HeroNode();
    }

	// 显示链表[遍历]
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空！！！");
			return;
		}
		// 由于头节点head不能动,所以需要一个辅助变量 temp
		HeroNode temp = head;
		while (true) {
			// 判断是否到链表的最后
			if (temp == null) {
				break;
			}
			// 如果不为空，输出节点的信息
			System.out.println(temp);
			// 注意！！！！将next后移(因为不向后移动,会造成死循环)
			temp = temp.next;
		}
	}

	// 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
	// (如果有这个排名，则添加失败，并给出提示)
	public void addByOrder(HeroNode heroNode) {
		// 由于头节点head不能动,所以需要一个辅助变量 temp,找到添加的位置
		// 由于是单链表，而找到temp是位于 添加位置的前一个节点,否则插入不了
		HeroNode temp = head;
		boolean flag = false; // 标识添加的编号是否存在，默认为false
		while (true) {
			if (temp.next == null) { // 说明已经在链表的最后
				break;
			}
			if (temp.next.id > heroNode.id) { // 位置找到了，就在temp的后面插入
				break;
			} else if (temp.next.id == heroNode.id) { // 说明希望添加的heroNode的编号已然存在
				flag = true; // 说明编号存在
				break;
			}
			temp = temp.next; // 后移,遍历当前链表
		}
		// 判断flag的值
		if (flag) { // 不能添加,说明编号存在
			System.out.printf("准备插入的英雄编号%d已经存在了。无法加入\n", heroNode.id);
		} else {
			// 插入到链表中,temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}
	
	//合并两个有序的单链表，合并之后的链表依然有序
    public SingleLinkedList merge(SingleLinkedList list1, SingleLinkedList list2) {
        if (list1.head.next == null) {
            return list2;
        } else if (list2.head.next == null) {
            return list1;
        }

        HeroNode newNode = new HeroNode();
        HeroNode n1 = newNode;
        HeroNode l1 = list1.head.next;
        HeroNode l2 = list2.head.next;

        while (l1 != null && l2 != null) {
            if (l1.id < l2.id) {
                n1.next = l1;
                l1 = l1.next;
                n1 = n1.next;
            } else {
                n1.next = l2;
                l2 = l2.next;
                n1 = n1.next;
            }
        }

        if (l1 == null) {
            n1.next = l2;
        }
        if (l2 == null) {
            n1.next = l1;
        }
        return new SingleLinkedList(newNode);
    }

}

// 定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
	public int id;
	public String name;
	public String nickName; // 别名,昵称
	public HeroNode next; // 指向下一个节点

	// 构造器
	public HeroNode(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}

	public HeroNode() {
	}

	// 为了显示方便，重写toString方法
	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}
}