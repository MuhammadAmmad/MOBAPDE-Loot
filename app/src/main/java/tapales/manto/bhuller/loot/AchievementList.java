package tapales.manto.bhuller.loot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AchievementList extends Fragment{
    RecyclerView rvAchievements;
    AchievementCursorAdapter achievementAdapter;
    AchievementCursorAdapter swapAdapter;
    DatabaseOpenHelper dbHelper;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.achievement_list, container, false);
        dbHelper = new DatabaseOpenHelper(v.getContext());
        achievementAdapter = new AchievementCursorAdapter(v.getContext(), dbHelper.getAllAchievements());
        rvAchievements = (RecyclerView) v.findViewById(R.id.recycler_achievements);
        rvAchievements.setAdapter(achievementAdapter);
        rvAchievements.setLayoutManager(new LinearLayoutManager(v.getContext()));
        return v;
    }
}