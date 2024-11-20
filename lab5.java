public class Main {

    public static void main(String[] args) {
        MainApplication app = new MainApplication("App");
        MainActivity activity = new MainActivity("App", "Activity");
        HomeFragment fragment = new HomeFragment("Home");
        DialogFragment dialogFragment = new DialogFragment("Dialog");

        app.onStart();

        activity.onStart();
        activity.onCreate(fragment);

        fragment.onAttach(activity);
        fragment.onStart();

        View view = fragment.onCreateView();
        fragment.onViewCreated(view);
        dialogFragment.show(fragment);
        fragment.onResume();

        activity.onPause();
        activity.onStop();
        activity.onResume(fragment);

        fragment.onDetach(activity);
        fragment.onDestroy();

        activity.onDestroy();
        app.onClose();
    }

    public static abstract class Activity extends Application {

        private final String nameActivity;

        public Activity(String nameApp, String nameActivity) {
            super(nameApp);
            this.nameActivity = nameActivity;
        }

        public void onStart() {
            System.out.println(nameActivity + " started.");
        }

        public Activity onCreate(Fragment fragment) {
            System.out.println(nameActivity + " created with fragment " + fragment.getNameFragment());
            return this;
        }

        public void onPause() {
            System.out.println(nameActivity + " paused.");
        }

        public void onStop() {
            System.out.println(nameActivity + " stopped.");
        }

        public void onResume(Fragment fragment) {
            System.out.println(nameActivity + " resumed with fragment " + fragment.getNameFragment());
        }

        public void onDestroy() {
            System.out.println(nameActivity + " destroyed.");
        }
    }

    public static class MainActivity extends Activity {

        public MainActivity(String nameApp, String nameActivity) {
            super(nameApp, nameActivity);
        }
    }

    public static abstract class Application {

        private final String nameApp;

        public Application(String nameApp) {
            this.nameApp = nameApp;
        }

        public void onStart() {
            System.out.println(nameApp + " started.");
        }

        public void onClose() {
            System.out.println(nameApp + " closed.");
        }
    }

    public static class MainApplication extends Application {

        public MainApplication(String nameApp) {
            super(nameApp);
        }
    }

    public static abstract class Fragment {

        private final String nameFragment;

        public Fragment(String nameFragment) {
            this.nameFragment = nameFragment;
        }

        public String getNameFragment() {
            return nameFragment;
        }

        public void onAttach(Activity activity) {
            System.out.println(nameFragment + " attached to " + activity.getClass().getSimpleName());
        }

        public void onStart() {
            System.out.println(nameFragment + " started.");
        }

        public View onCreateView() {
            System.out.println(nameFragment + " view created.");
            return new View();
        }

        public void onViewCreated(View view) {
            System.out.println(nameFragment + " view is now fully created.");
        }

        public void onDestroyView(View view) {
            System.out.println(nameFragment + " view destroyed.");
        }

        public void onPause() {
            System.out.println(nameFragment + " paused.");
        }

        public void onResume() {
            System.out.println(nameFragment + " resumed.");
        }

        public void onDetach(Activity activity) {
            System.out.println(nameFragment + " detached from " + activity.getClass().getSimpleName());
        }

        public void onDestroy() {
            System.out.println(nameFragment + " destroyed.");
        }
    }

    public static class HomeFragment extends Fragment {

        public HomeFragment(String nameFragment) {
            super(nameFragment);
        }
    }

    public static class DialogFragment extends Fragment {

        public DialogFragment(String nameFragment) {
            super(nameFragment);
        }

        public void show(Fragment fragment) {
            fragment.onPause();
            System.out.println("Dialog " + getNameFragment() + " shown over " + fragment.getNameFragment());
        }
    }

    public static class View {
    }
}